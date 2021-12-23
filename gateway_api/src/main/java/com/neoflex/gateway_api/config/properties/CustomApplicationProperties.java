package com.neoflex.gateway_api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application")
public class CustomApplicationProperties {

    private String ordersHost;
    private String customersHost;


}
