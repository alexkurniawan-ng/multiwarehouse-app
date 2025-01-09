package com.multiwarehouse.app.order.service.domain;

import com.multiwarehouse.app.domain.event.EmptyEvent;
import com.multiwarehouse.app.domain.valueobject.OrderId;
import com.multiwarehouse.app.order.service.domain.dto.message.PaymentResponse;
import com.multiwarehouse.app.order.service.domain.entity.Order;
import com.multiwarehouse.app.order.service.domain.event.OrderPaidEvent;
import com.multiwarehouse.app.order.service.domain.exception.OrderNotFoundException;
import com.multiwarehouse.app.order.service.domain.ports.output.message.publisher.sellerapproval.OrderPaidSellerRequestMessagePublisher;
import com.multiwarehouse.app.order.service.domain.ports.output.repository.OrderRepository;
import com.multiwarehouse.app.saga.SagaStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderPaymentSaga implements SagaStep<PaymentResponse, OrderPaidEvent, EmptyEvent> {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final OrderPaidSellerRequestMessagePublisher orderPaidSellerRequestMessagePublisher;

    public OrderPaymentSaga(OrderDomainService orderDomainService,
                            OrderRepository orderRepository,
                            OrderPaidSellerRequestMessagePublisher orderPaidSellerRequestMessagePublisher) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.orderPaidSellerRequestMessagePublisher = orderPaidSellerRequestMessagePublisher;
    }

    @Override
    @Transactional
    public OrderPaidEvent process(PaymentResponse paymentResponse) {
        log.info("Completing payment for order with id: {}", paymentResponse.getOrderId());
        Order order = findOrder(paymentResponse.getOrderId());
        OrderPaidEvent domainEvent = orderDomainService.payOrder(order, orderPaidSellerRequestMessagePublisher);
        orderRepository.save(order);
        log.info("Order with id: {} is paid", order.getId().getValue());
        return domainEvent;
    }

    @Override
    @Transactional
    public EmptyEvent rollback(PaymentResponse paymentResponse) {
        log.info("Cancelling order with id: {}", paymentResponse.getOrderId());
        Order order = findOrder(paymentResponse.getOrderId());
        orderDomainService.cancelOrder(order, paymentResponse.getFailureMessage());
        orderRepository.save(order);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
        return EmptyEvent.INSTANCE;
    }

    private Order findOrder(String orderId) {
        Optional<Order> orderResponse = orderRepository.findById(new OrderId(UUID.fromString(orderId)));
        if (orderResponse.isEmpty()) {
            log.error("Order with id: {} could not be found!", orderId);
            throw new OrderNotFoundException("Order with id " + orderId + " could not be found!");
        }
        return orderResponse.get();
    }
}