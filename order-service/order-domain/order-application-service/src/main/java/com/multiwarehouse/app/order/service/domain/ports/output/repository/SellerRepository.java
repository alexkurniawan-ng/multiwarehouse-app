package com.multiwarehouse.app.order.service.domain.ports.output.repository;

import com.multiwarehouse.app.order.service.domain.entity.Seller;

import java.util.Optional;

public interface SellerRepository {
    Optional<Seller> findSellerInformation(Seller seller);
}
