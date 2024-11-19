package com.multiwarehouse.app.payment.service.domain.dto.create;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CreatePaymentCommand {
    @NotNull
    private final UUID orderId;
    @NotNull
    private final UUID customerId;
    @NotNull
    private final BigDecimal amount;
    @NotNull
    private final String currency;
    @NotNull
    private final UUID paymentMethodId;
}
