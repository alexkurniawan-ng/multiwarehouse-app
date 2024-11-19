package com.multiwarehouse.app.payment.service.messaging.publisher.kafka;

import com.multiwarehouse.app.kafka.payment.avro.model.OrderRequestAvroModel;
import com.multiwarehouse.app.kafka.producer.KafkaMessageHelper;
import com.multiwarehouse.app.kafka.producer.service.KafkaProducer;
import com.multiwarehouse.app.payment.service.domain.config.PaymentServiceConfigData;
import com.multiwarehouse.app.payment.service.domain.event.PaymentCreatedEvent;
import com.multiwarehouse.app.payment.service.domain.ports.output.message.publisher.order.PaymentCreatedOrderRequestMessagePublisher;
import com.multiwarehouse.app.payment.service.messaging.mapper.PaymentMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreatePaymentKafkaMessagePublisher implements PaymentCreatedOrderRequestMessagePublisher {

    private final PaymentMessagingDataMapper paymentMessagingDataMapper;
    private final PaymentServiceConfigData paymentServiceConfigData;
    private final KafkaProducer<String, OrderRequestAvroModel> kafkaProducer;
    private final KafkaMessageHelper paymentKafkaMessageHelper;

    public CreatePaymentKafkaMessagePublisher(PaymentMessagingDataMapper paymentMessagingDataMapper,
                                              PaymentServiceConfigData paymentServiceConfigData,
                                              KafkaProducer<String, OrderRequestAvroModel> kafkaProducer,
                                              KafkaMessageHelper kafkaMessageHelper) {
        this.paymentMessagingDataMapper = paymentMessagingDataMapper;
        this.paymentServiceConfigData = paymentServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.paymentKafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(PaymentCreatedEvent domainEvent) {
        String paymentId = domainEvent.getPayment().getId().getValue().toString();
        log.info("Received PaymentCreatedEvent for payment id: {}", paymentId);

        try {
            OrderRequestAvroModel orderRequestAvroModel = paymentMessagingDataMapper
                    .paymentCreatedEventToOrderRequestAvroModel(domainEvent);

            kafkaProducer.send(paymentServiceConfigData.getOrderRequestTopicName(),
                    paymentId,
                    orderRequestAvroModel,
                    paymentKafkaMessageHelper
                            .getKafkaCallback(paymentServiceConfigData.getOrderResponseTopicName(),
                                    orderRequestAvroModel,
                                    paymentId,
                                    "OrderRequestAvroModel"));
            log.info("OrderRequestAvroModel sent to Kafka for payment id: {}", orderRequestAvroModel.getPaymentId());
        } catch (Exception e) {
            log.error("Error while sending OrderRequestAvroModel message" +
                    " to kafka with payment id: {}, error: {}", paymentId, e.getMessage());
        }
    }
}
