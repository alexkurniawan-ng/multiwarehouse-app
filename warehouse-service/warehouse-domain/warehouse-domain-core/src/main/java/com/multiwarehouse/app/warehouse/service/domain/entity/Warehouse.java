package com.multiwarehouse.app.warehouse.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import lombok.Builder;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.multiwarehouse.app.domain.DomainConstants.UTC;

public class Warehouse extends AggregateRoot<WarehouseId> {
    private final String name;
    private final UserId adminId;
    private final Address warehouseAddress;
    @Setter
    private ZonedDateTime createdAt;
    @Setter
    private ZonedDateTime updatedAt;

    @Builder
    public Warehouse(WarehouseId warehouseId, String name, UserId adminId, Address warehouseAddress, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        super.setId(warehouseId);
        this.name = name;
        this.adminId = adminId;
        this.warehouseAddress = warehouseAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void initializeWarehouse() {
        setId(new WarehouseId(UUID.randomUUID()));
        this.createdAt = ZonedDateTime.now(ZoneId.of(UTC));
    }

    public String getName() {
        return name;
    }

    public UserId getAdminId() {
        return adminId;
    }

    public Address getWarehouseAddress() {
        return warehouseAddress;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

}
