package com.multiwarehouse.app.payment.service.domain;

import com.multiwarehouse.app.payment.service.domain.dto.create.CreatePaymentCommand;
import com.multiwarehouse.app.payment.service.domain.dto.create.CreatePaymentResponse;
import com.multiwarehouse.app.payment.service.domain.ports.input.service.PaymentApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class PaymentApplicationServiceImpl implements PaymentApplicationService {

    private final PaymentCreateCommandHandler paymentCreateCommandHandler;

    PaymentApplicationServiceImpl(PaymentCreateCommandHandler paymentCreateCommandHandler) {
        this.paymentCreateCommandHandler = paymentCreateCommandHandler;
    }

    @Override
    public CreatePaymentResponse createPayment(CreatePaymentCommand createPaymentCommand) {
        return paymentCreateCommandHandler.createPayment(createPaymentCommand);
    }
}
