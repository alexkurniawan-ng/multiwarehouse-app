package com.multiwarehouse.app.payment.service.domain.ports.output.message.publisher.order;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.payment.service.domain.event.PaymentCreatedEvent;
//import org.springframework.stereotype.Component;
//
//@Component
public interface PaymentCreatedOrderRequestMessagePublisher extends DomainEventPublisher<PaymentCreatedEvent> {
}
