package com.example.ordersservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Order {

    @Id
    UUID id;

    @NotNull
    UUID customerId;

    @NotNull
    String orderStatus;

    @NotNull
    String deliveryAddress;

    @NotNull
    String cardDetails;

    @NotNull
    String dishesList;

    @NotNull
    String paymentStatus;

    @NotNull
    String restaurantStatus;

    @NotNull
    String deliveryStatus;
}
