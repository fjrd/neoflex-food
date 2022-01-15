package com.example.ordersservice.controller;

import com.example.ordersservice.controller.dto.dish.DishRequestDto;
import com.example.ordersservice.controller.dto.order.OrderRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.dto.payment.message.CardDetailMessageDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class OrderControllerIntegrationTest {

    private final MockMvc mockMvc;
    private final ObjectMapper mapper;
    private ObjectWriter ow;

    private OrderRequestDto requestDto1;
    private OrderRequestDto requestDto2;
    private UUID customerId;
    private UUID orderId1;
    private UUID orderId2;
    private UUID dishId1;
    private UUID dishId2;
    private String deliveryAddress;
    private CardDetailMessageDto cardDetailDto;
    private List<DishRequestDto> dishesList1;
    private List<DishRequestDto> dishesList2;
    private BigDecimal orderTotalCost;
    private String defaultStatus = "UNPROCESSED";

    private static PostgreSQLContainer ordersDb;
    private static GenericContainer redis;
    private static KafkaContainer kafka;


    @BeforeAll
    static void beforeAll() {
        ordersDb = new PostgreSQLContainer("postgres:latest")
                .withDatabaseName("postgres")
                .withUsername("postgres")
                .withPassword("postgres");
        ordersDb.addExposedPort(5433);
        ordersDb.setNetwork(Network.SHARED);

        redis = new GenericContainer("redis:latest")
                .withExposedPorts(6379)
                .withNetwork(Network.SHARED);

        kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))
                .withNetwork(Network.SHARED)
                .withEmbeddedZookeeper()
                .withExposedPorts(9092, 9093)
                .withEnv("KAFKA_ADVERTISED_HOST_NAME", "nf-kafka")
                .withEnv("KAFKA_CREATE_TOPIC", "orders");

        ordersDb.start();
        redis.start();
        kafka.start();
    }

    @AfterAll
    static void afterAll() {
        ordersDb.stop();
        redis.stop();
        kafka.stop();
    }

    @BeforeEach
    @SneakyThrows
    void setUp() {
        customerId = UUID.randomUUID();
        orderId1 = UUID.randomUUID();
        orderId2 = UUID.randomUUID();
        dishId1 = UUID.randomUUID();
        dishId2 = UUID.randomUUID();
        deliveryAddress = "some address";
        cardDetailDto = new CardDetailMessageDto(
                "123-456-789-901",
                "11/22",
                "Alexander",
                "Reztsov",
                "123");
        dishesList1 = List.of(
                DishRequestDto.builder()
                        .orderId(orderId1)
                        .dishId(dishId1)
                        .quantity(4)
                        .build(),
                DishRequestDto.builder()
                        .orderId(orderId1)
                        .dishId(dishId2)
                        .quantity(1)
                        .build());
        dishesList2 = List.of(
                DishRequestDto.builder()
                        .orderId(orderId2)
                        .dishId(dishId1)
                        .quantity(3)
                        .build(),
                DishRequestDto.builder()
                        .orderId(orderId2)
                        .dishId(dishId2)
                        .quantity(2)
                        .build());
        orderTotalCost = BigDecimal.valueOf(12.99);

        requestDto1 = OrderRequestDto.builder()
                .orderId(orderId1)
                .deliveryAddress(deliveryAddress)
                .cardDetails(cardDetailDto)
                .dishesList(dishesList1)
                .orderTotalCost(orderTotalCost)
                .build();

        requestDto2 = OrderRequestDto.builder()
                .orderId(orderId2)
                .deliveryAddress(deliveryAddress)
                .cardDetails(cardDetailDto)
                .dishesList(dishesList2)
                .orderTotalCost(orderTotalCost)
                .build();

        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = mapper.writer().withDefaultPrettyPrinter();
    }

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", ordersDb::getJdbcUrl);
        registry.add("spring.datasource.password", ordersDb::getPassword);
        registry.add("spring.datasource.username", ordersDb::getUsername);
        registry.add("spring.kafka.producer.bootstrap-servers", () -> kafka.getHost() + ":" + kafka.getMappedPort(KafkaContainer.KAFKA_PORT));
        registry.add("spring.kafka.consumer.bootstrap-servers", () -> kafka.getHost() + ":" + kafka.getMappedPort(KafkaContainer.KAFKA_PORT));
        registry.add("spring.redis.host", () -> redis.getHost());
        registry.add("spring.redis.port", () -> redis.getMappedPort(6379));
    }

    @Test
    void containersStartsCorrectly() {
    }

    @Test
    @SneakyThrows
    void getOrdersByCustomerIdWorkCorrectlyTest() {
        mockMvc.perform(post("/api/v1/orders")
                .header("x-user-id", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(requestDto1)));

        mockMvc.perform(post("/api/v1/orders")
                .header("x-user-id", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(requestDto2)));

        mockMvc.perform(get("/api/v1/orders/" + customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(orderId1.toString()))
                .andExpect(jsonPath("$[0].customerId").value(customerId.toString()))
                .andExpect(jsonPath("$[0].deliveryAddress").value(deliveryAddress))
                .andExpect(jsonPath("$[0].orderTime").exists())
                .andExpect(jsonPath("$[0].orderCounter").isNumber())
                .andExpect(jsonPath("$[0].orderTotalCost").value(orderTotalCost))
                .andExpect(jsonPath("$[0].dishesList[0].dishId").value(dishId1.toString()))
                .andExpect(jsonPath("$[0].dishesList[0].quantity").value(4))
                .andExpect(jsonPath("$[0].dishesList[1].dishId").value(dishId2.toString()))
                .andExpect(jsonPath("$[0].dishesList[1].quantity").value(1))
                .andExpect(jsonPath("$[0].orderStatus").value(defaultStatus))
                .andExpect(jsonPath("$[0].paymentStatus").value(defaultStatus))
                .andExpect(jsonPath("$[0].restaurantStatus").value(defaultStatus))
                .andExpect(jsonPath("$[0].deliveryStatus").value(defaultStatus))
                .andExpect(jsonPath("$[0].assignedCourierId").isEmpty())


                .andExpect(jsonPath("$[1].orderId").value(orderId2.toString()))
                .andExpect(jsonPath("$[1].customerId").value(customerId.toString()))
                .andExpect(jsonPath("$[1].deliveryAddress").value(deliveryAddress))
                .andExpect(jsonPath("$[1].orderTime").exists())
                .andExpect(jsonPath("$[1].orderCounter").isNumber())
                .andExpect(jsonPath("$[1].orderTotalCost").value(orderTotalCost))
                .andExpect(jsonPath("$[1].dishesList[0].dishId").value(dishId1.toString()))
                .andExpect(jsonPath("$[1].dishesList[0].quantity").value(3))
                .andExpect(jsonPath("$[1].dishesList[1].dishId").value(dishId2.toString()))
                .andExpect(jsonPath("$[1].dishesList[1].quantity").value(2))
                .andExpect(jsonPath("$[1].orderStatus").value(defaultStatus))
                .andExpect(jsonPath("$[1].paymentStatus").value(defaultStatus))
                .andExpect(jsonPath("$[1].restaurantStatus").value(defaultStatus))
                .andExpect(jsonPath("$[1].deliveryStatus").value(defaultStatus))
                .andExpect(jsonPath("$[1].assignedCourierId").isEmpty());
    }

    @Test
    @SneakyThrows
    void createOrderShouldReturnCorrectResponseDtoTest() {
        mockMvc.perform(post("/api/v1/orders")
                .header("x-user-id", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(requestDto1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(orderId1.toString()))
                .andExpect(jsonPath("$.customerId").value(customerId.toString()))
                .andExpect(jsonPath("$.orderStatus").value(defaultStatus))
                .andExpect(jsonPath("$.deliveryAddress").value(deliveryAddress))
                .andExpect(jsonPath("$.dishesList[0].dishId").value(dishId1.toString()))
                .andExpect(jsonPath("$.dishesList[0].quantity").value(4))
                .andExpect(jsonPath("$.dishesList[1].dishId").value(dishId2.toString()))
                .andExpect(jsonPath("$.dishesList[1].quantity").value(1))
                .andExpect(jsonPath("$.paymentStatus").value(defaultStatus))
                .andExpect(jsonPath("$.restaurantStatus").value(defaultStatus))
                .andExpect(jsonPath("$.deliveryStatus").value(defaultStatus))
                .andExpect(jsonPath("$.orderTime").exists())
                .andExpect(jsonPath("$.orderCounter").isNumber())
                .andExpect(jsonPath("$.orderTotalCost").value(String.valueOf(orderTotalCost)));
    }

    @Test
    @SneakyThrows
    void updateOrderShouldReturnCorrectResponseDtoTest() {
        mockMvc.perform(post("/api/v1/orders")
                .header("x-user-id", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(requestDto1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(orderId1.toString()))
                .andExpect(jsonPath("$.customerId").value(customerId.toString()))
                .andExpect(jsonPath("$.orderStatus").value(defaultStatus))
                .andExpect(jsonPath("$.deliveryAddress").value(deliveryAddress))
                .andExpect(jsonPath("$.dishesList[0].dishId").value(dishId1.toString()))
                .andExpect(jsonPath("$.dishesList[0].quantity").value(4))
                .andExpect(jsonPath("$.dishesList[1].dishId").value(dishId2.toString()))
                .andExpect(jsonPath("$.dishesList[1].quantity").value(1))
                .andExpect(jsonPath("$.paymentStatus").value(defaultStatus))
                .andExpect(jsonPath("$.restaurantStatus").value(defaultStatus))
                .andExpect(jsonPath("$.deliveryStatus").value(defaultStatus))
                .andExpect(jsonPath("$.orderTime").exists())
                .andExpect(jsonPath("$.orderCounter").isNumber())
                .andExpect(jsonPath("$.orderTotalCost").value(String.valueOf(orderTotalCost)));

        mockMvc.perform(get("/api/v1/orders/" + customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].deliveryAddress").value(deliveryAddress));

        mockMvc.perform(put("/api/v1/orders")
                .header("x-user-id", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(requestDto1.toBuilder().deliveryAddress("new address").build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deliveryAddress").value("new address"));

        mockMvc.perform(get("/api/v1/orders/" + customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].deliveryAddress").value("new address"));
    }

    @Test
    @SneakyThrows
    void createOrderWithSameIdShouldReturnBadRequestStatusTest() {
        mockMvc.perform(post("/api/v1/orders")
                .header("x-user-id", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(requestDto1)));

        mockMvc.perform(post("/api/v1/orders")
                .header("x-user-id", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(requestDto1)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @SneakyThrows
    void updateOrderShouldReturnBadRequestWhenOrderDoesNotBelongToCustomerTest() {
        mockMvc.perform(post("/api/v1/orders")
                .header("x-user-id", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(requestDto1)));

        mockMvc.perform(put("/api/v1/orders")
                .header("x-user-id", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(requestDto1.toBuilder().deliveryAddress("new address").build())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void updateOrderShouldReturnBadRequestWhenOrderDoesNotExistTest() {
        mockMvc.perform(put("/api/v1/orders")
                .header("x-user-id", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(requestDto1.toBuilder().deliveryAddress("new address").build())))
                .andExpect(status().isBadRequest());
    }
}
