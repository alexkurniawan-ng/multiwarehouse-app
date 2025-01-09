package com.multiwarehouse.app.payment.service.domain;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.payment.service.domain.entity.CreditEntry;
import com.multiwarehouse.app.payment.service.domain.entity.CreditHistory;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;
import com.multiwarehouse.app.payment.service.domain.event.PaymentCancelledEvent;
import com.multiwarehouse.app.payment.service.domain.event.PaymentCompletedEvent;
import com.multiwarehouse.app.payment.service.domain.event.PaymentEvent;
import com.multiwarehouse.app.payment.service.domain.event.PaymentFailedEvent;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages,
                                            DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher,
                                            DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages,
                                          DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
                                          DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher);
}