package com.multiwarehouse.app.order.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.OrderId;
import com.multiwarehouse.app.order.service.domain.entity.Order;
import com.multiwarehouse.app.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(OrderId orderId);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
