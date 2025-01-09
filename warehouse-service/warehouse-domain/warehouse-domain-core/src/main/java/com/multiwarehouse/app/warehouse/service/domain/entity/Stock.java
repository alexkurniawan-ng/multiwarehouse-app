package com.multiwarehouse.app.warehouse.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.StockId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.exception.StockDomainException;
import lombok.Builder;

import java.util.UUID;

public class Stock extends BaseEntity<StockId> {
    private final WarehouseId warehouseId;
    private final ProductId productId;
    private final Integer amount;

    @Builder
    public Stock(StockId stockId, WarehouseId warehouseId, ProductId productId, Integer amount) {
        super.setId(stockId);
        this.warehouseId = warehouseId;
        this.productId = productId;
        this.amount = amount;
    }

    public void initializeStock() { setId(new StockId(UUID.randomUUID())); }

    private void validateStockAmount(int size) {
        if (amount == null || amount < size) {
            throw new StockDomainException("Stock amount cannot be empty or less than amount");
        }
    }
}
