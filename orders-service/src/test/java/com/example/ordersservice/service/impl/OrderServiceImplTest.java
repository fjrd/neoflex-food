package com.example.ordersservice.service.impl;

import com.example.ordersservice.mapper.OrderMessageMapper;
import com.example.ordersservice.mapper.OrderRequestMapper;
import com.example.ordersservice.mapper.OrderResponseMapper;
import com.example.ordersservice.model.Customer;
import com.example.ordersservice.model.Order;
import com.example.ordersservice.repository.CustomerRepository;
import com.example.ordersservice.repository.OrderRepository;
import com.example.ordersservice.service.KafkaProducerService;
import lombok.SneakyThrows;
import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.order.request.OrderRequestDto;
import org.example.dto.order.response.OrderResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

class OrderServiceImplTest {

    @Mock
    private OrderMessageMapper orderMessageMapper ;
    @Mock
    private OrderRequestMapper orderRequestMapper;
    @Mock
    private OrderResponseMapper orderResponseMapper;
    @Mock
    private KafkaProducerService producer;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl service;

    private static final UUID CUSTOMER_ID = UUID.randomUUID();
    private static final UUID ORDER_ID = UUID.randomUUID();

    private OrderRequestDto requestDto;
    private OrderMessageDto messageDto;
    private OrderResponseDto responseDto;
    private Order order;
    private Customer customer;

    @SneakyThrows
    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        requestDto = OrderRequestDto.builder().orderId(ORDER_ID).build();
        customer = new Customer(CUSTOMER_ID);
        order = Order.builder().orderId(ORDER_ID).customer(customer).build();
        messageDto = OrderMessageDto.builder().orderId(ORDER_ID).customerId(CUSTOMER_ID).build();
        responseDto = OrderResponseDto.builder().orderId(ORDER_ID).customerId(CUSTOMER_ID).build();

        Mockito.when(orderRequestMapper.dtoToModel(requestDto)).thenAnswer(i -> order);
        Mockito.when(orderMessageMapper.modelToDto(order)).thenAnswer(i -> messageDto);
        Mockito.when(orderResponseMapper.modelToDto(order)).thenAnswer(i -> responseDto);
        Mockito.when(orderRepository.existsById(any())).thenAnswer(i -> false);
        Mockito.when(orderRepository.saveAndFlush(any())).thenAnswer(i -> order);
        Mockito.when(orderRepository.getAllByCustomer(any())).thenAnswer(i -> List.of(order));
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(order));
        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenAnswer(i -> Optional.of(customer));
    }

    @Test
    void createOrderCallsOrderRepositoryAndProducerTest() {

        assertThat(service.createOrder(CUSTOMER_ID, requestDto))
                .isNotNull()
                .isExactlyInstanceOf(OrderResponseDto.class);
        Mockito.verify(orderRepository, Mockito.times(1)).saveAndFlush(any(Order.class));
        Mockito.verify(producer, Mockito.times(1)).send(any(OrderMessageDto.class));
    }

    @Test
    void createOrderThrowEntityExistsExcTest() {
        Mockito.when(orderRepository.existsById(any())).thenAnswer(i -> true);
        assertThrows(EntityExistsException.class, () -> service.createOrder(CUSTOMER_ID, requestDto));
    }

    @Test
    void getOrderByCustomerIdShouldReturnOrder() {
        assertThat(service.getOrdersByCustomerId(CUSTOMER_ID))
                .isNotNull()
                .contains(responseDto);
        Mockito.verify(orderRepository, Mockito.times(1)).getAllByCustomer(any());
    }

    @Test
    void getOrderByCustomerIdShouldThrowEntityNotFoundExcTest() {
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.getOrdersByCustomerId(CUSTOMER_ID));
    }

    @Test
    void updateOrderWorksCorrectlyTest() {
        assertThat(service.updateOrder(CUSTOMER_ID, requestDto))
                .isNotNull()
                .isExactlyInstanceOf(OrderResponseDto.class);
        Mockito.verify(orderRepository, Mockito.times(1)).saveAndFlush(any(Order.class));
        Mockito.verify(producer, Mockito.times(1)).send(any(OrderMessageDto.class));
    }

    @Test
    void updateOrderShouldThrowEntityNotFoundExceptionTest() {
        assertThrows(EntityNotFoundException.class, () -> service.updateOrder(UUID.randomUUID(), requestDto));

        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.updateOrder(CUSTOMER_ID, requestDto));
    }

    @Test
    void updateOrderFromProcessorCallsSaveTest() {
        service.updateOrderFromProcessor(messageDto);
        Mockito.verify(orderRepository, Mockito.times(1)).saveAndFlush(any(Order.class));
    }

    @Test
    void updateOrderFromProcessorShouldThrowEntityNotFoundExcTest() {
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.updateOrderFromProcessor(messageDto));
    }
}