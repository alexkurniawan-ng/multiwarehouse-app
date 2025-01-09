package com.multiwarehouse.app.customer.service;

import com.multiwarehouse.app.customer.service.domain.CustomerDomainService;
import com.multiwarehouse.app.customer.service.domain.CustomerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    }
}
