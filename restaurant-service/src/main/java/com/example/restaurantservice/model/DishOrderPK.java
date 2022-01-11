package com.example.restaurantservice.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode
public class DishOrderPK implements Serializable {

    @Column(name = "restaurant_order_id", nullable = false)
    private UUID restaurantOrderId;

    @Column(name = "dish_id")
    private UUID dishId;

}