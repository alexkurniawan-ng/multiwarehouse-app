package com.multiwarehouse.app.customer.service.domain.ports.input.service;

import com.multiwarehouse.app.customer.service.domain.create.CreateCustomerCommand;
import com.multiwarehouse.app.customer.service.domain.create.CreateCustomerResponse;

import javax.validation.Valid;

public interface CustomerApplicationService {

    CreateCustomerResponse createCustomer(@Valid CreateCustomerCommand createCustomerCommand);

}
