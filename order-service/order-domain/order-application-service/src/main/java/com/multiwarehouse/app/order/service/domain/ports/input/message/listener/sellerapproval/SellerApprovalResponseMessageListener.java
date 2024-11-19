package com.multiwarehouse.app.order.service.domain.ports.input.message.listener.sellerapproval;

import com.multiwarehouse.app.order.service.domain.dto.message.SellerApprovalResponse;

public interface SellerApprovalResponseMessageListener {
    void orderApproved(SellerApprovalResponse sellerApprovalResponse);

    void orderRejected(SellerApprovalResponse sellerApprovalResponse);
}
