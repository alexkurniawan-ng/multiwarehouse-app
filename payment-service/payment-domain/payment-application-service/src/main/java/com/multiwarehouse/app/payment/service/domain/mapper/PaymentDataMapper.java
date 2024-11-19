package com.multiwarehouse.app.payment.service.domain.mapper;

import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.OrderId;
import com.multiwarehouse.app.payment.service.domain.dto.create.CreatePaymentCommand;
import com.multiwarehouse.app.payment.service.domain.dto.create.CreatePaymentResponse;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataMapper {
    public Payment createPaymentCommandToPay(CreatePaymentCommand createPaymentCommand) {
        return Payment.builder()
                .orderId(new OrderId(createPaymentCommand.getOrderId()))
                .amount(new Money(createPaymentCommand.getAmount()))
                .currency(createPaymentCommand.getCurrency())
                .build();

    }

    public CreatePaymentResponse paymentToCreatePaymentResponse(Payment payment, String message) {
        return CreatePaymentResponse.builder()
                .paymentStatus(payment.getPaymentStatus())
                .message(message)
                .build();
    }

}
