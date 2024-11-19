package com.multiwarehouse.app.payment.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentCreatedEvent extends PaymentEvent {

    private final DomainEventPublisher<PaymentCreatedEvent> paymentCreatedEventDomainEventPublisher;

    public PaymentCreatedEvent(Payment payment,
                               ZonedDateTime createdAt,
                               DomainEventPublisher<PaymentCreatedEvent> paymentCreatedEventDomainEventPublisher) {
        super(payment, createdAt);
        this.paymentCreatedEventDomainEventPublisher = paymentCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() { paymentCreatedEventDomainEventPublisher.publish(this); }
}
