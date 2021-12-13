package com.neoflex.gateway_api.config;

import com.neoflex.gateway_api.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("http://localhost:8090/"))
                .route("auth2", r -> r.path("**/orders/").filters(f -> f.filter(filter)).uri("http://nf-orders-service:8081/"))
                .build();
    }

}
