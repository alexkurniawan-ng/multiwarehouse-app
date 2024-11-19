package com.multiwarehouse.app.payment.service.dataaccess.payment.adapter;

import com.multiwarehouse.app.domain.valueobject.PaymentId;
import com.multiwarehouse.app.payment.service.domain.ports.output.repository.PaymentRepository;
import com.multiwarehouse.app.payment.service.dataaccess.payment.mapper.PaymentDataAccessMapper;
import com.multiwarehouse.app.payment.service.dataaccess.payment.repository.PaymentJpaRepository;
import com.multiwarehouse.app.payment.service.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentDataAccessMapper paymentDataAccessMapper;

    public PaymentRepositoryImpl(PaymentJpaRepository paymentJpaRepository,
                                 PaymentDataAccessMapper paymentDataAccessMapper) {
        this.paymentJpaRepository = paymentJpaRepository;
        this.paymentDataAccessMapper = paymentDataAccessMapper;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentDataAccessMapper.paymentEntityToPayment(paymentJpaRepository
                .save(paymentDataAccessMapper.paymentToPaymentEntity(payment)));
    }

    @Override
    public Optional<Payment> findById(PaymentId paymentId) {
        return paymentJpaRepository.findById(paymentId.getValue()).map(paymentDataAccessMapper::paymentEntityToPayment);
    }

}
