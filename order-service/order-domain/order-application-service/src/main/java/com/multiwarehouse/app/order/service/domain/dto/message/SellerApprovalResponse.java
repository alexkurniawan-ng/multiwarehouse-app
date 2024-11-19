package com.multiwarehouse.app.order.service.domain.dto.message;

import com.multiwarehouse.app.domain.valueobject.OrderApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class SellerApprovalResponse {
    private final String id;
//    private final String sagaId;
    private final String orderId;
    private final String sellerId;
    private final Instant createdAt;
    private final OrderApprovalStatus orderApprovalStatus;
    private final List<String> failureMessage;
}
