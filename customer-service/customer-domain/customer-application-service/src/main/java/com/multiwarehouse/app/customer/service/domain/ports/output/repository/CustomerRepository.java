package com.multiwarehouse.app.customer.service.domain.ports.output.repository;

import com.multiwarehouse.app.customer.service.domain.entity.Customer;

public interface CustomerRepository {

    Customer createCustomer(Customer customer);
}
