package com.multiwarehouse.app.payment.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentConfirmedEvent extends PaymentEvent {

    private final DomainEventPublisher<PaymentConfirmedEvent> paymentConfirmedEventDomainEventPublisher;

    public PaymentConfirmedEvent(Payment payment,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<PaymentConfirmedEvent> paymentConfirmedEventDomainEventPublisher) {
        super(payment, createdAt);
        this.paymentConfirmedEventDomainEventPublisher = paymentConfirmedEventDomainEventPublisher;
    }

    @Override
    public void fire() { paymentConfirmedEventDomainEventPublisher.publish(this); }
}
