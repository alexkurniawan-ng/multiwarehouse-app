package com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.repository;

import com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WarehouseJpaRepository extends JpaRepository<WarehouseEntity, UUID> {
}
