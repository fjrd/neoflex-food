package com.example.restaurantservice.model;

import lombok.*;
import org.example.dto.payment.PaymentStatus;
import org.example.dto.restaurant.RestaurantOrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant_orders")
public class RestaurantOrder implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "restaurant_order_id", nullable = false)
    private UUID restaurantOrderId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @Column(name = "order_counter", nullable = false)
    private Integer orderCounter;

    @Column(name = "order_total_cost", nullable = false)
    private BigDecimal orderTotalCost;

    @OneToMany(mappedBy = "restaurantOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DishOrder> dishesList;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.UNPROCESSED;

    @Enumerated(EnumType.STRING)
    @Column(name = "restaurant_status", nullable = false)
    private RestaurantOrderStatus restaurantStatus;

}
