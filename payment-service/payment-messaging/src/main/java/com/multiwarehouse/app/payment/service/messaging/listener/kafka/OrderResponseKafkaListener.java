package com.multiwarehouse.app.payment.service.messaging.listener.kafka;

import com.multiwarehouse.app.kafka.consumer.KafkaConsumer;
import com.multiwarehouse.app.kafka.payment.avro.model.OrderResponseAvroModel;
import com.multiwarehouse.app.kafka.payment.avro.model.OrderStatus;
import com.multiwarehouse.app.payment.service.domain.ports.input.message.listener.order.OrderResponseMessageListener;
import com.multiwarehouse.app.payment.service.messaging.mapper.PaymentMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OrderResponseKafkaListener implements KafkaConsumer<OrderResponseAvroModel> {

//    private final OrderResponseMessageListener orderResponseMessageListener;
    private final PaymentMessagingDataMapper paymentMessagingDataMapper;

    public OrderResponseKafkaListener(
//            OrderResponseMessageListener orderResponseMessageListener,
                                      PaymentMessagingDataMapper paymentMessagingDataMapper) {
//        this.orderResponseMessageListener = orderResponseMessageListener;
        this.paymentMessagingDataMapper = paymentMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.order-consumer-group-id}", topics = "${payment-service.order-response-topic-name}")
    public void receive(@Payload List<OrderResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(orderResponseAvroModel -> {
            if (OrderStatus.COMPLETED == orderResponseAvroModel.getOrderStatus()) {
                log.info("Processing successful payment for order id: {}", orderResponseAvroModel.getPaymentId());
//                orderResponseMessageListener.orderCompleted(paymentMessagingDataMapper
//                        .orderResponseAvroModelToOrderResponse(orderResponseAvroModel));
            } else if (OrderStatus.CANCELLED == orderResponseAvroModel.getOrderStatus() ||
                    OrderStatus.FAILED == orderResponseAvroModel.getOrderStatus()) {
                log.info("Processing unsuccessful order for payment id: {}", orderResponseAvroModel.getPaymentId());
//                orderResponseMessageListener.orderCancelled(paymentMessagingDataMapper
//                        .orderResponseAvroModelToOrderResponse(orderResponseAvroModel));
            }
        });
    }
}
