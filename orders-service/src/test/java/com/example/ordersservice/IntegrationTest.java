package com.example.ordersservice;

import com.example.ordersservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.UUID;

@Disabled
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class IntegrationTest {


    private static PostgreSQLContainer pgContainer;
    private static GenericContainer redis;
    private static GenericContainer zookeper;
    private static GenericContainer kafka;
    private final OrderRepository repository;


    @BeforeAll
    static void beforeAll() {

        pgContainer = new PostgreSQLContainer("postgres:latest")
                .withDatabaseName("postgres")
                .withUsername("postgres")
                .withPassword("postgres");

//        redis = new GenericContainer("redis:latest")
//                .withExposedPorts(6379);
//
//        zookeper = new GenericContainer("wurstmeister/zookeeper:latest")
//                .withExposedPorts(2181);
//
//        kafka = new KafkaContainer(DockerImageName.parse("wurstmeister/kafka:latest"))
//                .dependsOn(zookeper)
//                .withExposedPorts(9092);

        pgContainer.start();
        redis.start();
        zookeper.start();
        kafka.start();
    }

    @AfterAll
    static void afterAll() {
        pgContainer.stop();
        redis.stop();
        zookeper.stop();
        kafka.stop();
    }

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", pgContainer::getJdbcUrl);
        registry.add("spring.datasource.password", pgContainer::getPassword);
        registry.add("spring.datasource.username", pgContainer::getUsername);
    }

    @Test
    void name() {
        System.out.println(repository.findById(UUID.randomUUID()));
    }
}
