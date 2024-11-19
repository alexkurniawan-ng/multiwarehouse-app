package com.multiwarehouse.app.domain.valueobject;

import java.util.UUID;

public class SellerId extends BaseId<UUID> {
    public SellerId(UUID value) { super(value); }
}