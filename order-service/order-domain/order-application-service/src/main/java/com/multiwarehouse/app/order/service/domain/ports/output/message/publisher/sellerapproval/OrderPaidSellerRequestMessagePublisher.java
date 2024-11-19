package com.multiwarehouse.app.order.service.domain.ports.output.message.publisher.sellerapproval;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidSellerRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
