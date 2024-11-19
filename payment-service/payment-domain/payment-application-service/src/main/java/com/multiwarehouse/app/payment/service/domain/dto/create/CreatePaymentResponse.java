package com.multiwarehouse.app.payment.service.domain.dto.create;

import com.multiwarehouse.app.domain.valueobject.PaymentStatus;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreatePaymentResponse {
    @NotNull
    private final UUID paymentId;
    @NotNull
    private final PaymentStatus paymentStatus;
    @NotNull
    private final String message;
}
