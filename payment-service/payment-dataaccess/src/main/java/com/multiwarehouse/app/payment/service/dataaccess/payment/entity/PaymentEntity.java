package com.multiwarehouse.app.payment.service.dataaccess.payment.entity;


import com.multiwarehouse.app.domain.valueobject.PaymentStatus;
import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
@Entity
public class PaymentEntity {
    @Id
    private UUID id;
    private UUID orderId;
    private UUID customerId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String currency;
    private String failureMessages;

//    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
//    private


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
