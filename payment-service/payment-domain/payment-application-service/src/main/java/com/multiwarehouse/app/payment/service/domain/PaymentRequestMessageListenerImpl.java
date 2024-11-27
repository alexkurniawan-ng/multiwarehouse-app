package com.multiwarehouse.app.payment.service.domain;

import com.multiwarehouse.app.payment.service.domain.dto.message.PaymentRequest;
import com.multiwarehouse.app.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;

public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

    private final PaymentRequestHelper paymentRequestHelper;

    public PaymentRequestMessageListenerImpl(PaymentRequestHelper paymentRequestHelper) {
        this.paymentRequestHelper = paymentRequestHelper;
    }

    @Override
    public  void completePayment(PaymentRequest paymentRequest) {
        PaymentEvent paymentEvent = paymentRequestHelper.persistPayment(paymentRequest);
        //fireEvent(paymentEvent)'
    }

    @Override
    public void cancelPayment(PaymentRequest paymentRequest) {
        PaymentEvent paymentEvent = paymentRequestHelper.persistCancelPayment(paymentRequest);
        //fireEvent(paymentEvent);
    }

//    private void fireEvent(PaymentEvent paymentEvent) {
//        log.info("Publishing payment event with payment id: {} and order: {}",
//                paymentEvent.getPayment().getId().getValue(),
//                paymentEvent.getPayment().getOrderId(),getValue());
//        paymentEvent.fire();
//    }
}
