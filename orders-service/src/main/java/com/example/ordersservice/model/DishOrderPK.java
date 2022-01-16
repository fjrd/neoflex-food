package com.example.ordersservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DishOrderPK implements Serializable {

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "dish_id")
    private UUID dishId;

}
