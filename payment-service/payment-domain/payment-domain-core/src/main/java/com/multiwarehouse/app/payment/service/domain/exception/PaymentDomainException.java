package com.multiwarehouse.app.payment.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class PaymentDomainException extends DomainException {

    public PaymentDomainException(String message) { super(message); }

    public PaymentDomainException(String message, Throwable cause) { super(message, cause); }
}
