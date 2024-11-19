package com.multiwarehouse.app.order.service.domain.ports.output.message.publisher.payment;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.order.service.domain.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {

//    @Override
//    public void publish(OrderCreatedEvent event) {
//        // Add logic to publish the OrderCreatedEvent, e.g., sending it to a message broker or logging
//        System.out.println("Publishing order created event: " + event);
//    }
}
