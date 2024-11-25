package com.multiwarehouse.app.order.service.domain;

import com.multiwarehouse.app.order.service.domain.dto.message.PaymentResponse;
import com.multiwarehouse.app.order.service.domain.ports.input.message.listener.payment.PaymentResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Slf4j
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
//
//    private final OrderPaymentSaga orderPaymentSaga;
//
//    public PaymentResponseMessageListenerImpl(OrderPaymentSaga orderPaymentSaga) {
//        this.orderPaymentSaga = orderPaymentSaga;
//    }
    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {

    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {

    }
}

// Response from payment service