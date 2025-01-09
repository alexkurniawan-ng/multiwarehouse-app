package com.multiwarehouse.app.customer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = { "com.ecommerce.app.customer.service.dataaccess", "com.ecommerce.app.dataaccess"})
@EntityScan(basePackages = { "com.ecommerce.app.customer.service.dataaccess", "com.ecommerce.app.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.ecommerce.app")
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
