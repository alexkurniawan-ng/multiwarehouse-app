package com.multiwarehouse.app.payment.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditHistoryId extends BaseId<UUID> {
    public CreditHistoryId(UUID value) {super(value); }
}
