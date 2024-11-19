package com.multiwarehouse.app.payment.service.domain;

import com.multiwarehouse.app.payment.service.domain.dto.create.CreatePaymentCommand;
import com.multiwarehouse.app.payment.service.domain.dto.create.CreatePaymentResponse;
import com.multiwarehouse.app.payment.service.domain.event.PaymentCreatedEvent;
import com.multiwarehouse.app.payment.service.domain.mapper.PaymentDataMapper;
import com.multiwarehouse.app.payment.service.domain.ports.output.message.publisher.order.PaymentCreatedOrderRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentCreateCommandHandler {

    private final PaymentCreateHelper paymentCreateHelper;

    private final PaymentDataMapper paymentDataMapper;

    private final PaymentCreatedOrderRequestMessagePublisher paymentCreatedOrderRequestMessagePublisher;

    public PaymentCreateCommandHandler(PaymentCreateHelper paymentCreateHelper,
                                       PaymentDataMapper paymentDataMapper,
                                       PaymentCreatedOrderRequestMessagePublisher paymentCreatedOrderRequestMessagePublisher) {
        this.paymentCreateHelper = paymentCreateHelper;
        this.paymentDataMapper = paymentDataMapper;
        this.paymentCreatedOrderRequestMessagePublisher = paymentCreatedOrderRequestMessagePublisher;
    }

    public CreatePaymentResponse createPayment(CreatePaymentCommand createPaymentCommand) {
        PaymentCreatedEvent paymentCreatedEvent = paymentCreateHelper.persistPayment(createPaymentCommand);
        log.info("Payment is created with id: {}", paymentCreatedEvent.getPayment().getId().getValue());
        paymentCreatedOrderRequestMessagePublisher.publish(paymentCreatedEvent);
        return paymentDataMapper.paymentToCreatePaymentResponse(paymentCreatedEvent.getPayment(),
                "Payment Created Successfully");
    }
}
