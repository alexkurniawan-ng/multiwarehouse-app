package com.multiwarehouse.app.order.service.dataaccess.customer.mapper;

import com.multiwarehouse.app.domain.valueobject.CustomerId;
import com.multiwarehouse.app.order.service.dataaccess.customer.entity.CustomerEntity;
import com.multiwarehouse.app.order.service.domain.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {
    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
