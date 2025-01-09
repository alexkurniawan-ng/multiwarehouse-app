package com.multiwarehouse.app.warehouse.service.domain.exception;

import com.multiwarehouse.app.domain.exception.DomainException;

public class StockDomainException extends DomainException {
    public StockDomainException(String message) {
        super(message);
    }
    public StockDomainException(String message, Throwable cause) { super(message, cause); }
}
