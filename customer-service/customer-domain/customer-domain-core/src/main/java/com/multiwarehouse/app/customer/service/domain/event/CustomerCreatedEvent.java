package com.multiwarehouse.app.customer.service.domain.event;

import com.multiwarehouse.app.customer.service.domain.entity.Customer;
import com.multiwarehouse.app.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public class CustomerCreatedEvent implements DomainEvent<Customer> {

    private final Customer customer;

    private final ZonedDateTime createdAt;

    public CustomerCreatedEvent(Customer customer, ZonedDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void fire() {

    }
}
