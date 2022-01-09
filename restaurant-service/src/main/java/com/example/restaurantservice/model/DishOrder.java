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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dishId")
    private Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restaurantOrderId")
    private RestaurantOrder restaurantOrder;

}
