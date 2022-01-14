package org.example.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    UNPROCESSED("Order has not been processed yet"),
    IN_PROGRESS("Order processing in progress"),
    COMPLETED("Order completed successfully"),
    REJECTED("Order rejected");

    private String statusText;
}
