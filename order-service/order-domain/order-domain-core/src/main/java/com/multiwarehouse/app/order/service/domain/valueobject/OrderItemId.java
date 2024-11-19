package com.multiwarehouse.app.order.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) { super(value); }
}
