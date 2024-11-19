package com.multiwarehouse.app.order.service.domain.dto.message;

import com.multiwarehouse.app.domain.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private final String id;

//    private final String sagaId;

    private final String orderId;

    private final String paymentId;

    private final String customerId;

    private final BigDecimal price;

    private final Instant createdAt;

    private final PaymentStatus paymentStatus;

    private final List<String> failureMessage;
}
