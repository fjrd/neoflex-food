package com.example.ordersservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "dish_orders")
public class DishOrder {

    @EmbeddedId
    private DishOrderPK id;

    @Min(1)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dishId")
    private Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    private Order order;


}
