package org.example.dto.restaurant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RestaurantStatus {

    UNPROCESSED("Restaurant order has not been processed yet"),
    PENDING("Restaurant order pending"),
    COOKING("Cooking in progress"),
    DONE("Cooking is completed");

    private String statusText;

}
