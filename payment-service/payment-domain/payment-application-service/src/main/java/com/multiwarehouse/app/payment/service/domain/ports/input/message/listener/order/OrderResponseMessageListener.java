package com.multiwarehouse.app.payment.service.domain.ports.input.message.listener.order;

import com.multiwarehouse.app.payment.service.domain.dto.message.OrderResponse;

public interface OrderResponseMessageListener {

    void orderCompleted(OrderResponse paymentResponse);

    void orderCancelled(OrderResponse paymentResponse);

}
