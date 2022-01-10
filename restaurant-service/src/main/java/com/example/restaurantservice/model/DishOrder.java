package com.example.restaurantservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "dish_orders")
public class DishOrder implements Serializable {

    @EmbeddedId
    private DishOrderPK id;

    @Min(1)
    private Integer quantity;

    @MapsId("dishId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @MapsId("restaurantOrderId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_order_id")
    private RestaurantOrder restaurantOrder;

}
