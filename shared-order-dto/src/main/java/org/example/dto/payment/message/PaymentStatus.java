package org.example.dto.payment.message;

public enum PaymentStatus {
    UNPROCESSED("unprocessed"),
    SUCCESS("success"),
    FAIL("fail");

    PaymentStatus(String status) {
    }
}
