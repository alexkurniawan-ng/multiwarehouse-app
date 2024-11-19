package com.multiwarehouse.app.payment.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.*;
import com.multiwarehouse.app.payment.service.domain.exception.PaymentDomainException;
import com.multiwarehouse.app.payment.service.domain.valueobject.PaymentMethodId;

import java.util.List;
import java.util.UUID;

public class Payment extends AggregateRoot<PaymentId> {
    private final OrderId orderId;
    private final CustomerId customerId;
    private final Money amount;
    private final String currency;
    private final PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;
    private List<String> failureMessages;

    public static final String FAILURE_MESSAGE_DELIMITER = ",";

    public void initializePayment() {
        setId(new PaymentId(UUID.randomUUID()));
        paymentStatus = PaymentStatus.PENDING;
    }

    public void validatePayment() {
        validateInitialPayment();
        validateTotalAmount();
    }

    public void confirm() {
        if (paymentStatus != PaymentStatus.PENDING) {
            throw new PaymentDomainException("Payment is not in correct state for confirm operation!");
        }
        paymentStatus = PaymentStatus.COMPLETED;
    }

    public void initRefund() {
        if (paymentStatus != PaymentStatus.COMPLETED) {
            throw new PaymentDomainException("Payment is not in correct state for initRefund operation!");
        }
        paymentStatus = PaymentStatus.AWAITING_REFUND;
    }

    public void refund() {
        if (paymentStatus != PaymentStatus.AWAITING_REFUND) {
            throw new PaymentDomainException("Payment is not in correct state for refund operation!");
        }
        paymentStatus = PaymentStatus.REFUNDED;
    }

    public void initCancel(List<String> failureMessages) {
        if (paymentStatus != PaymentStatus.COMPLETED) {
            throw new PaymentDomainException("Payment is not in correct state for initCancel operation!");
        }
        paymentStatus = PaymentStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    public void cancel(List<String> failureMessages) {
        if (!(paymentStatus == PaymentStatus.CANCELLING || paymentStatus == PaymentStatus.PENDING)) {
            throw new PaymentDomainException("Payment is not in correct state for cancel operation!");
        }
        paymentStatus = PaymentStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages != null && failureMessages != null) {
            this.failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
        }
        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    private void validateInitialPayment() {
        if (paymentStatus != null || getId() != null) {
            throw new PaymentDomainException("Payment is not in correct state for initialization");
        }
    }

    private void validateTotalAmount() {
        if (amount == null || !amount.isGreaterThanZero()) {
            throw new PaymentDomainException("Total amount must be greater than zero!");
        }
    }

    private Payment(Builder builder) {
        super.setId(builder.paymentId);
        orderId = builder.orderId;
        customerId = builder.customerId;
        amount = builder.amount;
        currency = builder.currency;
        paymentMethod = builder.paymentMethod;
        paymentStatus = builder.paymentStatus;
        failureMessages = builder.failureMessages;
    }

    public static Builder builder() { return new Builder(); }

    public OrderId getOrderId() { return orderId; }

    public CustomerId getCustomerId() { return customerId; }

    public Money getAmount() { return amount; }

    public String getCurrency() { return currency; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }

    public List<String> getFailureMessages() { return failureMessages; }

    public static final class Builder {
        private PaymentId paymentId;
        private OrderId orderId;
        private CustomerId customerId;
        private Money amount;
        private String currency;
        private PaymentMethod paymentMethod;
        private PaymentStatus paymentStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder paymentId(PaymentId val) {
            paymentId = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder amount(Money val) {
            amount = val;
            return this;
        }

        public Builder currency(String val) {
            currency = val;
            return this;
        }

        public Builder paymentMethod(PaymentMethod val) {
            paymentMethod = val;
            return this;
        }

        public Builder paymentStatus(PaymentStatus val) {
            paymentStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Payment build() { return new Payment(this); }
    }

}
