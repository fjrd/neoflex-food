package org.example.dto.delivery;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DeliveryStatus {

    UNPROCESSED("Delivery has not been processed yet"),
    PENDING("Delivery pending"),
    IN_PROGRESS("Order is on the way"),
    SUCCESS("Delivery completed successfully");

    private String statusText;

}
