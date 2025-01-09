package com.multiwarehouse.app.payment.service.domain.ports.output.message.publisher;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.payment.service.domain.event.PaymentCancelledEvent;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}