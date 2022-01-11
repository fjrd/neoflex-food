package com.neoflex.deliveryservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoflex.deliveryservice.model.DeliveryOrder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapAddress;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        return props;
    }

    @Bean
    public ConsumerFactory<String, DeliveryOrder> applicationConsumerFactory() {
        final JsonDeserializer<DeliveryOrder> jsonDeserializer = new JsonDeserializer<>(objectMapper);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DeliveryOrder>
    applicationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DeliveryOrder> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(applicationConsumerFactory());
        return factory;
    }
}
