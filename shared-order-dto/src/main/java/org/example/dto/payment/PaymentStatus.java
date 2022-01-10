package org.example.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatus {

    CANCELED("Payment was canceled"),
    REJECTED("Payment was rejected"),
    UNPROCESSED("Payment has not been processed yet"),
    SUCCESS("Payment completed successfully"),
    FAIL("Payment failed");

    private String statusText;

}
