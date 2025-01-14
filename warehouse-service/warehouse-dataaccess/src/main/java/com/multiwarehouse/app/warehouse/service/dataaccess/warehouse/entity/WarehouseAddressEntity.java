package com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouse_address")
@Entity
public class WarehouseAddressEntity {
    @Id
    private UUID id;
    private String street;
    private String city;
    private String province;
    private String postalCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private WarehouseEntity warehouse;
}
