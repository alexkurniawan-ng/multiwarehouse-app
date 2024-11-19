package com.multiwarehouse.app.order.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException(String message) {
        super(message);
    }
    public OrderNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
