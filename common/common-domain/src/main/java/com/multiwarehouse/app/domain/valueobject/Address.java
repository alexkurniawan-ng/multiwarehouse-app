package com.multiwarehouse.app.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class Address {
    private final UUID id;
    private final String street;
    private final String postalCode;
    private final String city;
    private final String province;

    public Address(UUID id,
                   String street,
                   String city,
                   String province,
                   String postalCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() { return province; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(postalCode, address.postalCode) && Objects.equals(city, address.city) && Objects.equals(province, address.province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, postalCode, city, province);
    }
}
