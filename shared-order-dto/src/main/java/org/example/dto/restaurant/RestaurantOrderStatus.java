package org.example.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RestaurantOrderStatus {

    UNPROCESSED("Restaurant order has not been processed yet"),
    ACCEPTED("Restaurant order accepted"),
    COOKING("Cooking in progress"),
    DONE("Cooking is completed");

    private String statusText;

}
