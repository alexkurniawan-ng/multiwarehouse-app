package com.multiwarehouse.app.payment.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.payment.service.domain.valueobject.PaymentMethodId;

public class PaymentMethod extends BaseEntity<PaymentMethodId> {

    private String name;

    private String description;

    public PaymentMethod(PaymentMethodId paymentMethodId, String name, String description) {
        super.setId(paymentMethodId);
        this.name = name;
        this.description = description;
    }

    public PaymentMethod(PaymentMethodId paymentMethodId) { super.setId(paymentMethodId); }

    public void updateWithConfirmedNameAndDescription(String name, String description) {
        this.name = name;
        this.description = description;
    }

//    private PaymentMethod(Builder builder) {
//        super.setId(builder.paymentMethodId);
//        name = builder.name;
//        description = builder.description;
//    }

//    public static Builder builder() { return new Builder(); }
//
//    public String getName() { return name; }
//
//    public String getDescription() { return description; }
//
//    public static final class Builder {
//        private PaymentMethodId paymentMethodId;
//        private String name;
//        private String description;
//
//        private Builder() {
//        }
//
//        public Builder paymentMethodId(PaymentMethodId val) {
//            paymentMethodId = val;
//            return this;
//        }
//
//        public Builder name(String val) {
//            name = val;
//            return this;
//        }
//
//        public Builder description(String val) {
//            description = val;
//            return this;
//        }
//
//        public PaymentMethod build() { return new PaymentMethod(this); }
//    }
}
