package com.example.payment_service.model;

import lombok.*;
import org.example.dto.payment.PaymentStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id", nullable = false)
    private UUID id;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "order_total_cost", nullable = false)
    private BigDecimal orderTotalCost;

    @Column(name = "payment_status", nullable = false)
    private PaymentStatus status;

}
