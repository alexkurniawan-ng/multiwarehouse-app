//package com.multiwarehouse.app.payment.service.domain;
//
//import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
//import com.multiwarehouse.app.payment.service.domain.event.PaymentFailedEvent;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class ApplicationDomainEventPublisher implements
//        ApplicationEventPublisherAware,
//        DomainEventPublisher<PaymentFailedEvent> {
//
//    private ApplicationEventPublisher applicationEventPublisher;
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        this.applicationEventPublisher = applicationEventPublisher;
//    }
//
//    @Override
//    public void publish(PaymentFailedEvent domainEvent) {
//        this.applicationEventPublisher.publishEvent(domainEvent);
//        log.info("PaymentCreatedEvent is published for order id: {}", domainEvent.getPayment()
//                .getId().getValue());
//    }
//}
