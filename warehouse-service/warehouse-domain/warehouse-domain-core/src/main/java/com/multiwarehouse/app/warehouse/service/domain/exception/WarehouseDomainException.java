package com.multiwarehouse.app.warehouse.service.domain.exception;

public class WarehouseDomainException extends RuntimeException {
    public WarehouseDomainException(String message) {
        super(message);
    }
    public WarehouseDomainException(String message, Throwable cause) { super(message, cause); }
}
