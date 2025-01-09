package com.multiwarehouse.app.payment.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID value) {
        super(value);
    }
}
