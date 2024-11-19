package com.multiwarehouse.app.payment.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class PaymentNotFoundException extends DomainException {
    public PaymentNotFoundException(String message) { super(message); }
    public PaymentNotFoundException(String message, Throwable cause) { super(message, cause); }
}
