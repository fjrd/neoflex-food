package com.neoflex.gateway_api.config;

import com.neoflex.gateway_api.config.properties.CustomApplicationProperties;
import com.neoflex.gateway_api.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private CustomApplicationProperties properties;

    @Autowired
    private JwtAuthenticationFilter authFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**")
                        .filters(f -> f.filter(authFilter))
                        .uri(properties.getCustomersHost()))
                .route("auth2", r -> r.path("/api/v1/orders/**")
                        .filters(f -> f.filter(authFilter))
                        .uri(properties.getOrdersHost()))
                .build();

    }

}
