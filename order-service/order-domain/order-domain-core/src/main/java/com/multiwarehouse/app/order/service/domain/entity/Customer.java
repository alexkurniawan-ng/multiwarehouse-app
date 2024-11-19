package com.multiwarehouse.app.order.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {
    public Customer() {

    }

    public Customer(CustomerId customerId) { super.setId(customerId); }
}
