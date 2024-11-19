package com.multiwarehouse.app.dataaccess.seller.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SellerEntityId.class)
@Table(name = "order_seller_m_view", schema = "seller")
@Entity
public class SellerEntity {

    @Id
    private UUID sellerId;
    @Id
    private UUID productId;
    private String sellerName;
    private Boolean sellerActive;
    private String productName;
    private BigDecimal productPrice;
    private Boolean productAvailable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerEntity that = (SellerEntity) o;
        return Objects.equals(sellerId, that.sellerId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerId, productId);
    }
}
