package com.multiwarehouse.app.payment.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.PaymentId;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;

import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(PaymentId paymentId);

}
