package com.multiwarehouse.app.order.service.messaging.listener.kafka;

import com.multiwarehouse.app.kafka.consumer.KafkaConsumer;
import com.multiwarehouse.app.kafka.order.avro.model.OrderApprovalStatus;
import com.multiwarehouse.app.kafka.order.avro.model.SellerApprovalResponseAvroModel;
//import com.multiwarehouse.app.order.service.domain.ports.input.message.listener.sellerapproval.SellerApprovalResponseMessageListener;
import com.multiwarehouse.app.order.service.domain.ports.input.message.listener.sellerapproval.SellerApprovalResponseMessageListener;
import com.multiwarehouse.app.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.multiwarehouse.app.order.service.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Slf4j
@Component
public class SellerApprovalResponseKafkaListener implements KafkaConsumer<SellerApprovalResponseAvroModel> {

    private final SellerApprovalResponseMessageListener sellerApprovalResponseMessageListener;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    public SellerApprovalResponseKafkaListener(
            SellerApprovalResponseMessageListener
                                                           sellerApprovalResponseMessageListener,
                                                   OrderMessagingDataMapper orderMessagingDataMapper) {
        this.sellerApprovalResponseMessageListener = sellerApprovalResponseMessageListener;
        this.orderMessagingDataMapper = orderMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.seller-approval-consumer-group-id}",
            topics = "${order-service.seller-approval-response-topic-name}")
    public void receive(@Payload List<SellerApprovalResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of seller approval responses received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(sellerApprovalResponseAvroModel -> {
            if (OrderApprovalStatus.APPROVED == sellerApprovalResponseAvroModel.getOrderApprovalStatus()) {
                log.info("Processing approved order for order id: {}",
                        sellerApprovalResponseAvroModel.getOrderId());
                sellerApprovalResponseMessageListener.orderApproved(orderMessagingDataMapper
                        .approvalResponseAvroModelToApprovalResponse(sellerApprovalResponseAvroModel));
            } else if (OrderApprovalStatus.REJECTED == sellerApprovalResponseAvroModel.getOrderApprovalStatus()) {
                log.info("Processing rejected order for order id: {}, with failure messages: {}",
                        sellerApprovalResponseAvroModel.getOrderId(),
                        String.join(FAILURE_MESSAGE_DELIMITER,
                                sellerApprovalResponseAvroModel.getFailureMessages()));
                sellerApprovalResponseMessageListener.orderRejected(orderMessagingDataMapper
                        .approvalResponseAvroModelToApprovalResponse(sellerApprovalResponseAvroModel));
            }
        });

    }
}