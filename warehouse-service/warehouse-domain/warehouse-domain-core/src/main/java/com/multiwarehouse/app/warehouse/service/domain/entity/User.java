package com.multiwarehouse.app.warehouse.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.Role;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import lombok.Builder;

public class User extends BaseEntity<UserId> {
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final Role role;
    private final WarehouseId warehouseId;

    @Builder
    public User(UserId userId,
                String userName,
                String firstName,
                String lastName,
                Role role,
                WarehouseId warehouseId
    ) {
        super.setId(userId);
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.warehouseId = warehouseId;
    }
}
