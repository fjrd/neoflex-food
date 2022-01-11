package com.neoflex.deliveryservice.model;

import lombok.*;
import org.example.dto.delivery.DeliveryStatus;
import org.example.dto.restaurant.RestaurantOrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery_orders")
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @NotNull
    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Enumerated
    @NotNull
    @Column(name = "restaurant_status", nullable = false)
    private RestaurantOrderStatus restaurantStatus;

    @Enumerated
    @Column(name = "delivery_status", nullable = false)
    private DeliveryStatus deliveryStatus;

    @NotNull
    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id", nullable = false)
    private Courier courier;

}