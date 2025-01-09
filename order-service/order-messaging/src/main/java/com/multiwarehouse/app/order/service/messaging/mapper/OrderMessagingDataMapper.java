package com.multiwarehouse.app.order.service.messaging.mapper;

import com.multiwarehouse.app.kafka.order.avro.model.*;
import com.multiwarehouse.app.order.service.domain.dto.message.PaymentResponse;
import com.multiwarehouse.app.order.service.domain.dto.message.SellerApprovalResponse;
import com.multiwarehouse.app.order.service.domain.entity.Order;
import com.multiwarehouse.app.order.service.domain.event.OrderCancelledEvent;
import com.multiwarehouse.app.order.service.domain.event.OrderCreatedEvent;
import com.multiwarehouse.app.order.service.domain.event.OrderPaidEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMessagingDataMapper {

    public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(OrderCreatedEvent orderCreatedEvent) {
        Order order = orderCreatedEvent.getOrder();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setSagaId(UUID.randomUUID())
//                .setSagaId("")
                .setCustomerId(order.getCustomerId().getValue())
                .setOrderId(order.getId().getValue())
                .setPrice(order.getPrice().getAmount())
                .setCreatedAt(orderCreatedEvent.getCreatedAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
                .build();
    }

    public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(OrderCancelledEvent orderCancelledEvent) {
        Order order = orderCancelledEvent.getOrder();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID())
//                .setSagaId("")
                .setSagaId(UUID.randomUUID())
                .setCustomerId(order.getCustomerId().getValue())
                .setOrderId(order.getId().getValue())
                .setPrice(order.getPrice().getAmount())
                .setCreatedAt(orderCancelledEvent.getCreatedAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
                .build();
    }

    public SellerApprovalRequestAvroModel
    orderPaidEventToSellerApprovalRequestAvroModel(OrderPaidEvent orderPaidEvent) {
        Order order = orderPaidEvent.getOrder();
        return SellerApprovalRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID())
//                .setSagaId("")
                .setSagaId(UUID.randomUUID())
                .setOrderId(order.getId().getValue())
                .setSellerId(order.getSellerId().getValue())
                .setOrderId(order.getId().getValue())
                .setSellerOrderStatus(com.multiwarehouse.app.kafka.order.avro.model.SellerOrderStatus
                        .valueOf(order.getOrderStatus().name()))
                .setProducts(order.getItems().stream().map(orderItem ->
                        com.multiwarehouse.app.kafka.order.avro.model.Product.newBuilder()
                                .setId(orderItem.getProduct().getId().getValue().toString())
                                .setQuantity(orderItem.getQuantity())
                                .build()).collect(Collectors.toList()))
                .setPrice(order.getPrice().getAmount())
                .setCreatedAt(orderPaidEvent.getCreatedAt().toInstant())
                .setSellerOrderStatus(SellerOrderStatus.PAID)
                .build();
    }

    public PaymentResponse paymentResponseAvroModelToPaymentResponse(PaymentResponseAvroModel
                                                                             paymentResponseAvroModel) {
        return PaymentResponse.builder()
                .id(paymentResponseAvroModel.getId().toString())
//                .sagaId(paymentResponseAvroModel.getSagaId())
                .sagaId(paymentResponseAvroModel.getSagaId().toString())
                .paymentId(paymentResponseAvroModel.getPaymentId().toString())
                .customerId(paymentResponseAvroModel.getCustomerId().toString())
                .orderId(paymentResponseAvroModel.getOrderId().toString())
                .price(paymentResponseAvroModel.getPrice())
                .createdAt(paymentResponseAvroModel.getCreatedAt())
                .paymentStatus(com.multiwarehouse.app.domain.valueobject.PaymentStatus.valueOf(
                        paymentResponseAvroModel.getPaymentStatus().name()))
                .failureMessage(paymentResponseAvroModel.getFailureMessages())
                .build();
    }

    public SellerApprovalResponse
    approvalResponseAvroModelToApprovalResponse(SellerApprovalResponseAvroModel
                                                        sellerApprovalResponseAvroModel) {
        return SellerApprovalResponse.builder()
                .id(sellerApprovalResponseAvroModel.getId().toString())
//                .sagaId(sellerApprovalResponseAvroModel.getSagaId())
                .sagaId(sellerApprovalResponseAvroModel.getSagaId().toString())
                .sellerId(sellerApprovalResponseAvroModel.getSellerId().toString())
                .orderId(sellerApprovalResponseAvroModel.getOrderId().toString())
                .createdAt(sellerApprovalResponseAvroModel.getCreatedAt())
                .orderApprovalStatus(com.multiwarehouse.app.domain.valueobject.OrderApprovalStatus.valueOf(
                        sellerApprovalResponseAvroModel.getOrderApprovalStatus().name()))
                .failureMessage(sellerApprovalResponseAvroModel.getFailureMessages())
                .build();
    }
}