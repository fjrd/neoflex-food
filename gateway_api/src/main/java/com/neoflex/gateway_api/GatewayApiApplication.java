package com.neoflex.gateway_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
//@EnableEurekaClient
@EnableFeignClients
public class GatewayApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayApiApplication.class, args);
	}
}
