package com.example.ordersservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @NotNull
    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @NotNull
    @Column(name = "dishes_list", nullable = false)
    private String dishesList;

    @Builder.Default
    @Column(name = "order_status", nullable = false)
    private String orderStatus = "unprocessed";

    @Builder.Default
    @Column(name = "payment_status", nullable = false)
    private String paymentStatus = "unprocessed";

    @Builder.Default
    @Column(name = "restaurant_status", nullable = false)
    private String restaurantStatus = "unprocessed";

    @Builder.Default
    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus = "unprocessed";

    @Builder.Default
    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime = LocalDateTime.now();

    @Generated(GenerationTime.INSERT)
    @Column(name = "order_counter", insertable = false)
    private Integer orderCounter;

}
