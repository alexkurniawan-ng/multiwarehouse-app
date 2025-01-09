package com.multiwarehouse.app.payment.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {
    public CreditEntryId(UUID value) {super(value); }
}
