package com.multiwarehouse.app.customer.service.dataaccess.adapter;

import com.multiwarehouse.app.customer.service.dataaccess.mapper.CustomerDataAccessMapper;
import com.multiwarehouse.app.customer.service.dataaccess.repository.CustomerJpaRepository;
import com.multiwarehouse.app.customer.service.domain.entity.Customer;
import com.multiwarehouse.app.customer.service.domain.ports.output.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    private final CustomerDataAccessMapper customerDataAccessMapper;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository,
                                  CustomerDataAccessMapper customerDataAccessMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerDataAccessMapper = customerDataAccessMapper;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDataAccessMapper.customerEntityToCustomer(
                customerJpaRepository.save(customerDataAccessMapper.customerToCustomerEntity(customer)));
    }
}