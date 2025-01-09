package com.multiwarehouse.app.payment.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.CustomerId;
import com.multiwarehouse.app.payment.service.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}