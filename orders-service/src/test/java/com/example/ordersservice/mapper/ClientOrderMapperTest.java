//package com.example.ordersservice.mapper;
//
//import com.example.ordersservice.model.Order;
//import dto.ClientOrderDto;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Disabled
//@SpringBootTest()
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//public class ClientOrderMapperTest {
//
//    private final ClientOrderMapper mapper;
//    private ClientOrderDto dto;
//    private static final String DEFAULT_STATUS = "unprocessed";
//
//    @BeforeEach
//    void setUp() {
//        dto = ClientOrderDto.builder()
//                .customerId(UUID.randomUUID())
//                .cardDetails("some card details")
//                .deliveryAddress("some address")
//                .dishesList("some dishes")
//                .build();
//    }
//
//    @Test
//    public void dtoToModelWorkCorrectWithMissingFields(){
//
//        Order order = mapper.dtoToModel(dto);
//
//        assertThat(order)
//                .isNotNull()
//                .matches(o -> o.getCustomerId().equals(dto.getCustomerId()))
//                .matches(o -> o.getCardDetails().equals(dto.getCardDetails()))
//                .matches(o -> o.getDeliveryAddress().equals(dto.getDeliveryAddress()))
//                .matches(o -> o.getDishesList().equals(dto.getDishesList()))
//                .matches(o -> o.getOrderStatus().equals(DEFAULT_STATUS))
//                .matches(o -> o.getPaymentStatus().equals(DEFAULT_STATUS))
//                .matches(o -> o.getRestaurantStatus().equals(DEFAULT_STATUS))
//                .matches(o -> o.getDeliveryStatus().equals(DEFAULT_STATUS));
//    }
//}
