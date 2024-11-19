package com.multiwarehouse.app.payment.service.domain;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.payment.service.domain.entity.Order;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;
import com.multiwarehouse.app.payment.service.domain.event.PaymentCreatedEvent;

public interface PaymentDomainService {

    PaymentCreatedEvent validateAndInitiatePayment(Payment payment, DomainEventPublisher<PaymentCreatedEvent> paymentCreatedEventDomainEventPublisher);
//    PaymentCreatedEvent validateAndInitiatePayment(Payment payment, Order order, DomainEventPublisher<PaymentCreatedEvent> paymentCreatedEventDomainEventPublisher);


}
