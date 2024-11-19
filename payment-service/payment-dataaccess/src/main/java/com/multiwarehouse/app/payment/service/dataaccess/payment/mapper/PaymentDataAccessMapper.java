package com.multiwarehouse.app.payment.service.dataaccess.payment.mapper;

import com.multiwarehouse.app.domain.valueobject.CustomerId;
import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.OrderId;
import com.multiwarehouse.app.domain.valueobject.PaymentId;
import com.multiwarehouse.app.payment.service.dataaccess.payment.entity.PaymentEntity;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

import static com.multiwarehouse.app.payment.service.domain.entity.Payment.FAILURE_MESSAGE_DELIMITER;

@Component
public class PaymentDataAccessMapper {

    public PaymentEntity paymentToPaymentEntity(Payment payment) {
        PaymentEntity paymentEntity = PaymentEntity.builder()
                .id(payment.getId().getValue())
                .orderId(payment.getOrderId().getValue())
                .customerId((payment.getCustomerId().getValue()))
                .currency(payment.getCurrency())
                .paymentStatus(payment.getPaymentStatus())
                .amount(payment.getAmount().getAmount())
                .failureMessages(payment.getFailureMessages() != null ?
                        String.join(FAILURE_MESSAGE_DELIMITER, payment.getFailureMessages()) : "")
                .build();
        return paymentEntity;
    }

    public Payment paymentEntityToPayment(PaymentEntity paymentEntity) {
        return Payment.builder()
                .paymentId(new PaymentId(paymentEntity.getId()))
                .orderId(new OrderId(paymentEntity.getOrderId()))
                .customerId(new CustomerId(paymentEntity.getCustomerId()))
                .amount(new Money(paymentEntity.getAmount()))
                .currency(paymentEntity.getCurrency())
                .paymentStatus(paymentEntity.getPaymentStatus())
                .failureMessages(paymentEntity.getFailureMessages().isEmpty() ? new ArrayList<>() :
                        new ArrayList<>(Arrays.asList(paymentEntity.getFailureMessages()
                                .split(FAILURE_MESSAGE_DELIMITER))))
                .build();
    }
}
