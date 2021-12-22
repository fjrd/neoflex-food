package com.example.ordersservice.controller;

import com.example.ordersservice.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.example.dto.order.request.OrderRequestDto;
import org.example.dto.payment.message.CardDetailDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @MockBean
    private static OrderService service;

    @InjectMocks
    private OrderController controller;
    private MockMvc mockMvc;
    private ObjectMapper mapper;

    private OrderRequestDto requestDto;
    private String requestDtoJsonString;
    private UUID customerId = UUID.randomUUID();

    @BeforeAll
    static void beforeAll() {
    }

    @BeforeEach
    @SneakyThrows
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        requestDtoJsonString = ow.writeValueAsString(requestDto);

        requestDto = OrderRequestDto.builder()
                .orderId(UUID.randomUUID())
                .deliveryAddress("some address")
                .dishesList("7 cheasy pizzas")
                .cardDetails(new CardDetailDto(
                        "123456789",
                        "11/12",
                        "FiestName",
                        "LastName",
                        "123"))
                .orderAmount(BigDecimal.valueOf(42.2))
                .build();

//        Mockito.when(service.createOrder(any(), any())).thenReturn();
    }


    @Test
    void getOrdersByCustomerId() {
    }

    @Test
    @SneakyThrows
    void createOrderShouldReturnCorrectDto() {
        mockMvc.perform(post("/api/v1/orders")
                .header("x-user-id", customerId )
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestDtoJsonString))
                .andExpect(status().isOk());
    }
}