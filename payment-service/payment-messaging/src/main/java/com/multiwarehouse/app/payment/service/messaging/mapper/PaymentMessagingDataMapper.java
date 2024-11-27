package com.multiwarehouse.app.payment.service.messaging.mapper;

import com.multiwarehouse.app.kafka.payment.avro.model.OrderRequestAvroModel;
import com.multiwarehouse.app.kafka.payment.avro.model.OrderResponseAvroModel;
import com.multiwarehouse.app.payment.service.domain.dto.message.PaymentRequest;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;
import com.multiwarehouse.app.payment.service.domain.event.PaymentCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMessagingDataMapper {

    public OrderRequestAvroModel paymentCreatedEventToOrderRequestAvroModel(PaymentCreatedEvent paymentCreatedEvent) {
        Payment payment = paymentCreatedEvent.getPayment();
        return OrderRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID())
//                .setSagaId("")
                .setCustomerId(payment.getId().getValue())
                .setPaymentId(payment.getId().getValue())
                .setAmount(payment.getAmount().getAmount())
                .setCreatedAt(paymentCreatedEvent.getCreatedAt().toInstant())
                .build();
    }


    public PaymentRequest orderResponseAvroModelToOrderResponse(OrderResponseAvroModel orderResponseAvroModel) {
        return PaymentRequest.builder()
                .id(orderResponseAvroModel.getId().toString())
//                .sagaId(orderResponseAvroModel.getSagaId())
                .orderId(orderResponseAvroModel.getOrderId().toString())
                .customerId(orderResponseAvroModel.getCustomerId().toString())
                .paymentId(orderResponseAvroModel.getPaymentId().toString())
                .amount(orderResponseAvroModel.getAmount())
                .createdAt(orderResponseAvroModel.getCreatedAt())
                .orderStatus(com.multiwarehouse.app.domain.valueobject.OrderStatus.valueOf(
                        orderResponseAvroModel.getOrderStatus().name()))
                .failureMessage(orderResponseAvroModel.getFailureMessages())
                .build();
    }
}
