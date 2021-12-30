package org.example.dto.order;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {

    UNPROCESSED("Order has not been processed yet"),
    IN_PROGRESS("Order processing in progress"),
    COMPLETED("Order completed successfully");

    private String statusText;
}
