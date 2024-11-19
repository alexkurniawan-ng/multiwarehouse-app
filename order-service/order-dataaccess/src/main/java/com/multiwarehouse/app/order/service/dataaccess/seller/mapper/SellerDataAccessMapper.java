package com.multiwarehouse.app.order.service.dataaccess.seller.mapper;

import com.multiwarehouse.app.dataaccess.seller.entity.SellerEntity;
import com.multiwarehouse.app.dataaccess.seller.exception.SellerDataAccessException;
import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.SellerId;
import com.multiwarehouse.app.order.service.domain.entity.Product;
import com.multiwarehouse.app.order.service.domain.entity.Seller;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SellerDataAccessMapper {

    public List<UUID> sellerToSellerProducts(Seller seller) {
        return seller.getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Seller sellerEntityToSeller(List<SellerEntity> sellerEntities) {
        SellerEntity sellerEntity =
                sellerEntities.stream().findFirst().orElseThrow(() ->
                        new SellerDataAccessException("Seller could not be found!"));

        List<Product> sellerProducts = sellerEntities.stream().map(entity ->
                new Product(new ProductId(entity.getProductId()), entity.getProductName(),
                        new Money(entity.getProductPrice()))).toList();

        return Seller.builder()
                .sellerId(new SellerId(sellerEntity.getSellerId()))
                .products(sellerProducts)
                .active(sellerEntity.getSellerActive())
                .build();
    }
}
