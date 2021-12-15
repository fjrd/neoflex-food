package com.example.ordersservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID customerId;

    @NotNull
    private String deliveryAddress;

    @NotNull
    private String dishesList;

    @Builder.Default
    private String orderStatus = "unprocessed";

    @Builder.Default
    private String paymentStatus = "unprocessed";

    @Builder.Default
    private String restaurantStatus = "unprocessed";

    @Builder.Default
    private String deliveryStatus = "unprocessed";
}
