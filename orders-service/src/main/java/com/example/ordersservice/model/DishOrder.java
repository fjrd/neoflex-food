package com.example.ordersservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"order"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
@Entity
@Table(name = "dish_orders")
public class DishOrder {


    @EmbeddedId
    @EqualsAndHashCode.Include
    private DishOrderPK id;

    @Min(1)
    private Integer quantity;

    @MapsId("dishId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "dish_id")
    private Dish dish;


    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
