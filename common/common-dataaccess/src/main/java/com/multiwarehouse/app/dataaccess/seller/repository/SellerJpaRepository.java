package com.multiwarehouse.app.dataaccess.seller.repository;

import com.multiwarehouse.app.dataaccess.seller.entity.SellerEntity;
import com.multiwarehouse.app.dataaccess.seller.entity.SellerEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerJpaRepository extends JpaRepository<SellerEntity, SellerEntityId> {

    Optional<List<SellerEntity>> findBySellerIdAndProductIdIn(UUID sellerId, List<UUID> productIds);
}
