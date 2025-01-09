package com.multiwarehouse.app.order.service.domain;

import com.multiwarehouse.app.order.service.domain.dto.create.CreateOrderCommand;
import com.multiwarehouse.app.order.service.domain.entity.*;
import com.multiwarehouse.app.order.service.domain.event.*;
import com.multiwarehouse.app.order.service.domain.exception.*;
import com.multiwarehouse.app.order.service.domain.mapper.OrderDataMapper;
import com.multiwarehouse.app.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.multiwarehouse.app.order.service.domain.ports.output.repository.CustomerRepository;
import com.multiwarehouse.app.order.service.domain.ports.output.repository.OrderRepository;
import com.multiwarehouse.app.order.service.domain.ports.output.repository.SellerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateHelper {
    private final OrderDomainService orderDomainService;

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final SellerRepository sellerRepository;

    private final OrderDataMapper orderDataMapper;

    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;
    private final ApplicationDomainEventPublisher applicationDomainEventPublisher;

    public OrderCreateHelper(OrderDomainService orderDomainService,
                             OrderRepository orderRepository,
                             CustomerRepository customerRepository,
                             SellerRepository sellerRepository,
                             OrderDataMapper orderDataMapper,
                             OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher, ApplicationDomainEventPublisher applicationDomainEventPublisher) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
        this.orderDataMapper = orderDataMapper;
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
        this.applicationDomainEventPublisher = applicationDomainEventPublisher;
    }

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Seller seller = checkSeller(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, seller,
                orderCreatedPaymentRequestMessagePublisher);
        saveOrder(order);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        applicationDomainEventPublisher.publish(orderCreatedEvent); // publish to KAFKA after saving to database
        return orderCreatedEvent;
    }

    private Seller checkSeller(CreateOrderCommand createOrderCommand) {
        Seller seller = orderDataMapper.createOrderCommandToSeller(createOrderCommand);
        Optional<Seller> optionalSeller = sellerRepository.findSellerInformation(seller);
        if (optionalSeller.isEmpty()) {
            log.warn("Could not find seller with seller id: {}", createOrderCommand.getSellerId());
            throw new OrderDomainException("Could not find seller with seller id: " +
                    createOrderCommand.getSellerId());
        }
        return optionalSeller.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()) {
            log.warn("Could not find customer with customer id: {}", customerId);
            throw new OrderDomainException("Could not find customer with customer id: " + customer);
        }
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}", orderResult.getId().getValue());
        return orderResult;
    }
}
