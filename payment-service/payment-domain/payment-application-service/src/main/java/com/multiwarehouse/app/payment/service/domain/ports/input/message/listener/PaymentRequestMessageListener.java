package com.multiwarehouse.app.payment.service.domain.ports.input.message.listener;

import com.multiwarehouse.app.payment.service.domain.dto.message.PaymentRequest;

public interface PaymentRequestMessageListener {

    void completePayment(PaymentRequest paymentRequest);

    void cancelPayment(PaymentRequest paymentRequest);

}
