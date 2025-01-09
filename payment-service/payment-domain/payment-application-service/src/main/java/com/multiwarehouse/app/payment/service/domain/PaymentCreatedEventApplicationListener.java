//package com.multiwarehouse.app.payment.service.domain;
//
//import com.multiwarehouse.app.payment.service.domain.event.PaymentFailedEvent;
//import com.multiwarehouse.app.payment.service.domain.ports.output.message.publisher.order.PaymentCreatedOrderRequestMessagePublisher;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.event.TransactionalEventListener;
//
//@Slf4j
//@Component
//public class PaymentCreatedEventApplicationListener {
//    private final PaymentCreatedOrderRequestMessagePublisher paymentCreatedOrderRequestMessagePublisher;
//
//    public PaymentCreatedEventApplicationListener(PaymentCreatedOrderRequestMessagePublisher
//                                                          paymentCreatedOrderRequestMessagePublisher) {
//        this.paymentCreatedOrderRequestMessagePublisher = paymentCreatedOrderRequestMessagePublisher;
//    }
//
//    @TransactionalEventListener
//    void process(PaymentFailedEvent paymentFailedEvent) {
//        paymentCreatedOrderRequestMessagePublisher.publish(paymentFailedEvent);
//    }
//}
