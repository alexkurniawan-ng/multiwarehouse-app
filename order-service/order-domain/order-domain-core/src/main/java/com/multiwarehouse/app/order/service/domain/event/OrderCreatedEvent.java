package com.multiwarehouse.app.order.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent {
    private final DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher;

    public OrderCreatedEvent(Order order,
                             ZonedDateTime createdAt,
                             DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher) {
        super(order, createdAt);
        this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        orderCreatedEventDomainEventPublisher.publish(this);
    }
}
