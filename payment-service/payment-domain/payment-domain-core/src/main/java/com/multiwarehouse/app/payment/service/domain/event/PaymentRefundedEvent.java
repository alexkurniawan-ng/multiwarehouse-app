package com.multiwarehouse.app.payment.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentRefundedEvent extends PaymentEvent {

    private final DomainEventPublisher<PaymentRefundedEvent> paymentRefundedEventDomainEventPublisher;

    public PaymentRefundedEvent(Payment payment,
                                ZonedDateTime createdAt,
                                DomainEventPublisher<PaymentRefundedEvent> paymentRefundedEventDomainEventPublisher) {
        super(payment, createdAt);
        this.paymentRefundedEventDomainEventPublisher = paymentRefundedEventDomainEventPublisher;
    }

    @Override
    public void fire() { paymentRefundedEventDomainEventPublisher.publish(this); }
}
