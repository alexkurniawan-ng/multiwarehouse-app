package com.multiwarehouse.app.payment.service.domain.dto.message;

import com.multiwarehouse.app.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class OrderResponse {
    private final String id;

//    private final String sagaId;

    private final String orderId;

    private final String paymentId;

    private final String customerId;

    private final BigDecimal amount;

    private final Instant createdAt;

    private final OrderStatus orderStatus;

    private final List<String> failureMessage;
}
