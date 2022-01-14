package com.example.restaurantservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"restaurantOrder"})
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "dish_orders")
public class DishOrder implements Serializable {

    @EqualsAndHashCode.Include
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
