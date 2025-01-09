package com.multiwarehouse.app.order.service.domain;

import com.multiwarehouse.app.domain.event.EmptyEvent;
import com.multiwarehouse.app.order.service.domain.dto.message.SellerApprovalResponse;
import com.multiwarehouse.app.order.service.domain.entity.Order;
import com.multiwarehouse.app.order.service.domain.event.OrderCancelledEvent;
import com.multiwarehouse.app.order.service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.multiwarehouse.app.saga.SagaStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderApprovalSaga implements SagaStep<SellerApprovalResponse, EmptyEvent, OrderCancelledEvent> {

    private final OrderDomainService orderDomainService;
    private final OrderSagaHelper orderSagaHelper;
    private final OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher;

    public OrderApprovalSaga(OrderDomainService orderDomainService,
                             OrderSagaHelper orderSagaHelper,
                             OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher) {
        this.orderDomainService = orderDomainService;
        this.orderSagaHelper = orderSagaHelper;
        this.orderCancelledPaymentRequestMessagePublisher = orderCancelledPaymentRequestMessagePublisher;
    }

    @Override
    @Transactional
    public EmptyEvent process(SellerApprovalResponse sellerApprovalResponse) {
        log.info("Approving order with id: {}", sellerApprovalResponse.getOrderId());
        Order order = orderSagaHelper.findOrder(sellerApprovalResponse.getOrderId());
        orderDomainService.approveOrder(order);
        orderSagaHelper.saveOrder(order);
        log.info("Order with id: {} is approved", order.getId().getValue());
        return EmptyEvent.INSTANCE;
    }

    @Override
    @Transactional
    public OrderCancelledEvent rollback(SellerApprovalResponse sellerApprovalResponse) {
        log.info("Rolling back order with id: {}", sellerApprovalResponse.getOrderId());
        Order order = orderSagaHelper.findOrder(sellerApprovalResponse.getOrderId());
        OrderCancelledEvent domainEvent = orderDomainService.cancelOrderPayment(order,
                sellerApprovalResponse.getFailureMessage(),
                orderCancelledPaymentRequestMessagePublisher);
        orderSagaHelper.saveOrder(order);
        log.info("Order with id: {} is cancelling", order.getId().getValue());
        return domainEvent;
    }
}
