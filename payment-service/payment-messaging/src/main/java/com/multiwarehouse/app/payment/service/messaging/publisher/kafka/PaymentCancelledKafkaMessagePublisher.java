package com.multiwarehouse.app.payment.service.messaging.publisher.kafka;

import com.multiwarehouse.app.kafka.order.avro.model.PaymentResponseAvroModel;
import com.multiwarehouse.app.kafka.producer.KafkaMessageHelper;
import com.multiwarehouse.app.kafka.producer.service.KafkaProducer;
import com.multiwarehouse.app.payment.service.domain.config.PaymentServiceConfigData;
import com.multiwarehouse.app.payment.service.domain.event.PaymentCancelledEvent;
import com.multiwarehouse.app.payment.service.domain.ports.output.message.publisher.PaymentCancelledMessagePublisher;
import com.multiwarehouse.app.payment.service.messaging.mapper.PaymentMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentCancelledKafkaMessagePublisher implements PaymentCancelledMessagePublisher {

    private final PaymentMessagingDataMapper paymentMessagingDataMapper;
    private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
    private final PaymentServiceConfigData paymentServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public PaymentCancelledKafkaMessagePublisher(PaymentMessagingDataMapper paymentMessagingDataMapper,
                                                 KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer,
                                                 PaymentServiceConfigData paymentServiceConfigData,
                                                 KafkaMessageHelper kafkaMessageHelper) {
        this.paymentMessagingDataMapper = paymentMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.paymentServiceConfigData = paymentServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(PaymentCancelledEvent domainEvent) {
        String orderId = domainEvent.getPayment().getOrderId().getValue().toString();

        log.info("Received PaymentCancelledEvent for order id: {}", orderId);

        try {
            PaymentResponseAvroModel paymentResponseAvroModel =
                    paymentMessagingDataMapper.paymentCancelledEventToPaymentResponseAvroModel(domainEvent);

            kafkaProducer.send(paymentServiceConfigData.getPaymentResponseTopicName(),
                    orderId,
                    paymentResponseAvroModel,
                    kafkaMessageHelper.getKafkaCallback(paymentServiceConfigData.getPaymentResponseTopicName(),
                            paymentResponseAvroModel,
                            orderId,
                            "PaymentResponseAvroModel"));

            log.info("PaymentResponseAvroModel sent to kafka for order id: {}", orderId);
        } catch (Exception e) {
            log.error("Error while sending PaymentResponseAvroModel message" +
                    " to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
