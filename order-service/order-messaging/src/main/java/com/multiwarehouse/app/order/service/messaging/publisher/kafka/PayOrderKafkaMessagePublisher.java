package com.multiwarehouse.app.order.service.messaging.publisher.kafka;

import com.multiwarehouse.app.kafka.order.avro.model.SellerApprovalRequestAvroModel;
import com.multiwarehouse.app.kafka.producer.KafkaMessageHelper;
import com.multiwarehouse.app.kafka.producer.service.KafkaProducer;
import com.multiwarehouse.app.order.service.domain.config.OrderServiceConfigData;
import com.multiwarehouse.app.order.service.domain.event.OrderPaidEvent;
import com.multiwarehouse.app.order.service.domain.ports.output.message.publisher.sellerapproval.OrderPaidSellerRequestMessagePublisher;
import com.multiwarehouse.app.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PayOrderKafkaMessagePublisher implements OrderPaidSellerRequestMessagePublisher {

    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaProducer<String, SellerApprovalRequestAvroModel> kafkaProducer;
    private final KafkaMessageHelper orderKafkaMessageHelper;

    public PayOrderKafkaMessagePublisher(OrderMessagingDataMapper orderMessagingDataMapper,
                                         OrderServiceConfigData orderServiceConfigData,
                                         KafkaProducer<String, SellerApprovalRequestAvroModel> kafkaProducer,
                                         KafkaMessageHelper orderKafkaMessageHelper) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.orderKafkaMessageHelper = orderKafkaMessageHelper;
    }

    @Override
    public void publish(OrderPaidEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();

        try {
            SellerApprovalRequestAvroModel sellerApprovalRequestAvroModel =
                    orderMessagingDataMapper.orderPaidEventToSellerApprovalRequestAvroModel(domainEvent);

            kafkaProducer.send(orderServiceConfigData.getSellerApprovalRequestTopicName(),
                    orderId,
                    sellerApprovalRequestAvroModel,
                    orderKafkaMessageHelper
                            .getKafkaCallback(orderServiceConfigData.getSellerApprovalRequestTopicName(),
                                    sellerApprovalRequestAvroModel,
                                    orderId,
                                    "SellerApprovalRequestAvroModel"));

            log.info("SellerApprovalRequestAvroModel sent to kafka for order id: {}", orderId);
        } catch (Exception e) {
            log.error("Error while sending SellerApprovalRequestAvroModel message" +
                    " to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
