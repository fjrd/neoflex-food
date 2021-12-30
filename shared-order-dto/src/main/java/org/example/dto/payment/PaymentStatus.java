package org.example.dto.payment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentStatus {

    UNPROCESSED("Payment has not been processed yet"),
    SUCCESS("Payment completed successfully"),
    FAIL("Payment failed");

    private String statusText;

}
