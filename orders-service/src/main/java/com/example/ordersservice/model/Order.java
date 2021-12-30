package com.example.ordersservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.payment.PaymentStatus;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", indexes = {
        @Index(name = "idx_order_order_time_unq", columnList = "order_time, order_counter", unique = true)
})
public class Order {

    private static final String DEFAULT_STATUS = "UNPROCESSED";

    @Id
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull
    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @NotNull
    @Column(name = "dishes_list", nullable = false)
    private String dishesList;

    @Builder.Default
    @Column(name = "order_status", nullable = false)
    private String orderStatus = DEFAULT_STATUS;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.UNPROCESSED;

    @Builder.Default
    @Column(name = "restaurant_status", nullable = false)
    private String restaurantStatus = DEFAULT_STATUS;

    @Builder.Default
    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus = DEFAULT_STATUS;

    @Builder.Default
    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime = LocalDateTime.now();

    @Generated(GenerationTime.INSERT)
    @Column(name = "order_counter", nullable = false, insertable = false)
    private Integer orderCounter;

    @Min(0)
    @Column(name = "order_amount")
    private BigDecimal orderAmount;

}
