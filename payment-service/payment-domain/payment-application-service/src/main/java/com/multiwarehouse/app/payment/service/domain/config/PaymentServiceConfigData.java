package com.multiwarehouse.app.payment.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "payment-service")
public class PaymentServiceConfigData {
    private String orderRequestTopicName;
    private String orderResponseTopicName;
//    private String sellerApprovalRequestTopicName;
//    private String sellerApprovalResponseTopicName;
}
