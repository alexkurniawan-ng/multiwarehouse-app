package com.multiwarehouse.app.payment.service.domain;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.payment.service.domain.entity.Order;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;
import com.multiwarehouse.app.payment.service.domain.event.PaymentCreatedEvent;
import com.multiwarehouse.app.payment.service.domain.exception.PaymentDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.multiwarehouse.app.domain.DomainConstants.UTC;

//@Slf4j
public class PaymentDomainServiceImpl implements PaymentDomainService {
    @Override
    public PaymentCreatedEvent validateAndInitiatePayment(Payment payment,
//                                                          Order order,
                                                          DomainEventPublisher<PaymentCreatedEvent>
                                                          paymentCreatedEventDomainEventPublisher) {
        payment.validatePayment();
        payment.initializePayment();
//        log.info("Payment with id: {} is initiated", payment.getId().getValue());
        return new PaymentCreatedEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)), paymentCreatedEventDomainEventPublisher);
    }


}
