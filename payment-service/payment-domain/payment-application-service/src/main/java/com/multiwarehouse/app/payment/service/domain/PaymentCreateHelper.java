package com.multiwarehouse.app.payment.service.domain;

import com.multiwarehouse.app.payment.service.domain.dto.create.CreatePaymentCommand;
import com.multiwarehouse.app.payment.service.domain.entity.Order;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;
import com.multiwarehouse.app.payment.service.domain.event.PaymentCreatedEvent;
import com.multiwarehouse.app.payment.service.domain.mapper.PaymentDataMapper;
import com.multiwarehouse.app.payment.service.domain.ports.output.message.publisher.order.PaymentCreatedOrderRequestMessagePublisher;
import com.multiwarehouse.app.payment.service.domain.ports.output.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class PaymentCreateHelper {

    private final PaymentDomainService paymentDomainService;

//    private final PaymentRepository paymentRepository;

    private final PaymentDataMapper paymentDataMapper;

    private final PaymentCreatedOrderRequestMessagePublisher paymentCreatedEventDomainEventPublisher;

    public PaymentCreateHelper(PaymentDomainService paymentDomainService,
//                               PaymentRepository paymentRepository,
                               PaymentDataMapper paymentDataMapper,
                               PaymentCreatedOrderRequestMessagePublisher paymentCreatedOrderRequestMessagePublisher) {
        this.paymentDomainService = paymentDomainService;
//        this.paymentRepository = paymentRepository;
        this.paymentDataMapper = paymentDataMapper;
        this.paymentCreatedEventDomainEventPublisher = paymentCreatedOrderRequestMessagePublisher;
    }

    @Transactional
    public PaymentCreatedEvent persistPayment(CreatePaymentCommand createPaymentCommand) {
        // implementations

        Payment payment = paymentDataMapper.createPaymentCommandToPay(createPaymentCommand);
//        return paymentDomainService.validateAndInitiatePayment(payment, new Order(),
//                paymentCreatedEventDomainEventPublisher);
        PaymentCreatedEvent paymentCreatedEvent = paymentDomainService.validateAndInitiatePayment(payment,
                paymentCreatedEventDomainEventPublisher);
        return paymentCreatedEvent;
    }
}
