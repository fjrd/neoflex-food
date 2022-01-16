package com.example.restaurantservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dishes")
@Entity
public class Dish implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dish_id", nullable = false)
    private UUID dishId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "is_on_the_menu", nullable = false)
    private Boolean isOnTheMenu;

}
