//package com.example.ordersservice.controller;
//
//import com.example.ordersservice.service.OrderService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import org.example.dto.order.request.OrderRequestDto;
//import org.example.dto.payment.message.CardDetailDto;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@ExtendWith({MockitoExtension.class, SpringExtension.class})
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//class OrderControllerTest {
//
//    @MockBean
//    private static OrderService service;
//
//    @InjectMocks
//    private final OrderController controller;
//    private final MockMvc mockMvc;
//    private final ObjectMapper mapper;
//
//    private OrderRequestDto requestDto;
//    private String requestDtoJsonString;
//
//    @BeforeAll
//    static void beforeAll() {
//    }
//
//    @BeforeEach
//    @SneakyThrows
//    void setUp() {
//        requestDto = OrderRequestDto.builder()
//                .deliveryAddress("some address")
//                .dishesList("7 cheasy pizzas")
//                .cardDetails(new CardDetailDto(
//                        "123456789",
//                        "11/12",
//                        "FiestName",
//                        "LastName",
//                        "123"))
//                .build();
//
//        mapper.findAndRegisterModules();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        requestDtoJsonString = ow.writeValueAsString(requestDto);
//
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void getOrdersByCustomerId() {
//    }
//
//    @Test
//    @SneakyThrows
//    void createOrderShouldReturnCorrectDto() {
//        mockMvc.perform(post("/api/v1/orders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestDtoJsonString))
//                .andExpect(status().isOk());
//    }
//}