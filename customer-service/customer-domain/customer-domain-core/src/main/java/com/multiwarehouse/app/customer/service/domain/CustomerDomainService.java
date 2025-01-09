package com.multiwarehouse.app.customer.service.domain;

import com.multiwarehouse.app.customer.service.domain.entity.Customer;
import com.multiwarehouse.app.customer.service.domain.event.CustomerCreatedEvent;

public interface CustomerDomainService {

    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

}