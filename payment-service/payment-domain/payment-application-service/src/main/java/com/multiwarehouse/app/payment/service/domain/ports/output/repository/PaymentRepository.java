package com.multiwarehouse.app.payment.service.domain.ports.output.repository;

import com.multiwarehouse.app.payment.service.domain.entity.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findByOrderId(UUID orderId);
}
