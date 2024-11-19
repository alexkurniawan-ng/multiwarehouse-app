package com.multiwarehouse.app.order.service.dataaccess.seller.adapter;

import com.multiwarehouse.app.dataaccess.seller.entity.SellerEntity;
import com.multiwarehouse.app.dataaccess.seller.repository.SellerJpaRepository;
import com.multiwarehouse.app.order.service.dataaccess.seller.mapper.SellerDataAccessMapper;
import com.multiwarehouse.app.order.service.domain.entity.Seller;
import com.multiwarehouse.app.order.service.domain.ports.output.repository.SellerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SellerRepositoryImpl implements SellerRepository {

    private final SellerJpaRepository sellerJpaRepository;
    private final SellerDataAccessMapper sellerDataAccessMapper;

    public SellerRepositoryImpl(SellerJpaRepository sellerJpaRepository,
                                SellerDataAccessMapper sellerDataAccessMapper) {
        this.sellerJpaRepository = sellerJpaRepository;
        this.sellerDataAccessMapper = sellerDataAccessMapper;
    }

    @Override
    public Optional<Seller> findSellerInformation(Seller seller) {
        List<UUID> sellerProducts =
                sellerDataAccessMapper.sellerToSellerProducts(seller);
        Optional<List<SellerEntity>> sellerEntities = sellerJpaRepository
                .findBySellerIdAndProductIdIn(seller.getId().getValue(),
                        sellerProducts);
        return sellerEntities.map(sellerDataAccessMapper::sellerEntityToSeller);
    }
}
