package com.neoflex.gateway_api.config;

import com.neoflex.gateway_api.filter.JwtAuthenticationFilter;
import com.neoflex.gateway_api.filter.LoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter authFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8092/"))
                .route("auth2", r -> r.path("/api/v1/orders")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8081/"))
                .build();

    }

}
