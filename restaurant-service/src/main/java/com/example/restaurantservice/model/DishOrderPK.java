package com.example.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class DishOrderPK implements Serializable {

    @Column(name = "restaurant_order_id", nullable = false)
    private UUID restaurantOrderId;

    @Column(name = "dish_id")
    private UUID dishId;

}
