package com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.mapper;

import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.entity.WarehouseAddressEntity;
import com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataAccessMapper {
    public WarehouseEntity warehouseToWarehouseEntity(Warehouse warehouse) {
        WarehouseAddressEntity addressEntity = addressToAddressEntity(warehouse.getWarehouseAddress());
        WarehouseEntity warehouseEntity = WarehouseEntity.builder()
                .id(warehouse.getId().getValue())
                .adminId(warehouse.getAdminId().getValue())
                .address(addressEntity)
                .createdAt(warehouse.getCreatedAt())
                .updatedAt(warehouse.getUpdatedAt())
                .build();
        addressEntity.setWarehouse(warehouseEntity);
        return warehouseEntity;
    }

    public Warehouse warehouseEntityToWarehouse(WarehouseEntity entity) {
        return Warehouse.builder()
                .warehouseId(new WarehouseId(entity.getId()))
                .adminId(new UserId(entity.getAdminId()))
                .warehouseAddress(AddressEntityToAddress(entity.getAddress()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private Address AddressEntityToAddress(WarehouseAddressEntity addressEntity) {
        return new Address(addressEntity.getId(),
                addressEntity.getStreet(),
                addressEntity.getCity(),
                addressEntity.getProvince(),
                addressEntity.getPostalCode());
    }

    private WarehouseAddressEntity addressToAddressEntity(Address address) {
        return WarehouseAddressEntity.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .province(address.getProvince())
                .postalCode(address.getPostalCode())
                .build();
    }
}
