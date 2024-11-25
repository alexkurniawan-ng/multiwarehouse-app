package com.multiwarehouse.app.order.service.domain;

import com.multiwarehouse.app.order.service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.multiwarehouse.app.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.multiwarehouse.app.order.service.domain.ports.output.message.publisher.sellerapproval.OrderPaidSellerRequestMessagePublisher;
import com.multiwarehouse.app.order.service.domain.ports.output.repository.CustomerRepository;
import com.multiwarehouse.app.order.service.domain.ports.output.repository.OrderRepository;
import com.multiwarehouse.app.order.service.domain.ports.output.repository.SellerRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.multiwarehouse.app")
public class OrderTestConfiguration {
    @Bean
    public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher(){
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher(){
        return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
    }
    @Bean
    public OrderPaidSellerRequestMessagePublisher orderPaidSellerRequestMessagePublisher(){
        return Mockito.mock(OrderPaidSellerRequestMessagePublisher.class);
    }
    @Bean
    public OrderRepository orderRepository(){
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository(){
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public SellerRepository sellerRepository(){
        return Mockito.mock(SellerRepository.class);
    }

    @Bean
    public OrderDomainService orderDomainService(){
        return new OrderDomainServiceImpl();
    }

}
