package com.multiwarehouse.app.warehouse.service.domain.event;

import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;

import java.time.ZonedDateTime;

public class WarehouseUpdatedEvent extends WarehouseEvent {
    public WarehouseUpdatedEvent(Warehouse warehouse,
                                 ZonedDateTime createdAt) {
        super(warehouse, createdAt);
    }

    @Override
    public void fire() {};
}
