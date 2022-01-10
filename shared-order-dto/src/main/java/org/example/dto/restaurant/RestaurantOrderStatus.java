package org.example.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RestaurantOrderStatus {

    CANCELED("Restaurant order was canceled"),
    REJECTED("Restaurant order was rejected"),
    UNPROCESSED("Restaurant order has not been processed yet"),
    ACCEPTED("Restaurant order accepted"),
    COOKING("Cooking in progress"),
    SUCCESS("Cooking is completed");

    private String statusText;

}
