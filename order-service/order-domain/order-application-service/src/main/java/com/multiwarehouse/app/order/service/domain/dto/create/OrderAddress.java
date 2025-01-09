package com.multiwarehouse.app.order.service.domain.dto.create;

//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
public class OrderAddress {
    @NotNull
    @Max(value = 50)
//    @NotNull(message = "Name {jakarta.validation.constraints.NotNull.message}")
//    @Size(min = 2, max = 50, message = "Name {jakarta.validation.constraints.Size.message}")
    private final String street;
//    @NotNull(message = "Name {jakarta.validation.constraints.NotNull.message}")
//    @Size(min = 2, max = 10, message = "Name {jakarta.validation.constraints.Size.message}")
    @NotNull
    @Max(value = 50)
    private final String postalCode;
//    @NotNull(message = "Name {jakarta.validation.constraints.NotNull.message}")
//    @Size(min = 2, max = 50, message = "Name {jakarta.validation.constraints.Size.message}")
    @NotNull
    @Max(value = 50)
    private final String city;

    @NotNull
    @Max(value = 50)
    private final String province;
}
