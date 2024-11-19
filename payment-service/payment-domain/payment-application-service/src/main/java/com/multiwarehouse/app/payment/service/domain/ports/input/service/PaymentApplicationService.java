package com.multiwarehouse.app.payment.service.domain.ports.input.service;

import com.multiwarehouse.app.payment.service.domain.dto.create.CreatePaymentCommand;
import com.multiwarehouse.app.payment.service.domain.dto.create.CreatePaymentResponse;
import javax.validation.Valid;

public interface PaymentApplicationService {
    CreatePaymentResponse createPayment(@Valid CreatePaymentCommand createPaymentCommand);

}
