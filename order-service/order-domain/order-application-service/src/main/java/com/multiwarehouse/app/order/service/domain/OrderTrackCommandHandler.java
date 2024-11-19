package com.multiwarehouse.app.order.service.domain;

import com.multiwarehouse.app.order.service.domain.dto.track.TrackOrderQuery;
import com.multiwarehouse.app.order.service.domain.dto.track.TrackOrderResponse;
import com.multiwarehouse.app.order.service.domain.entity.Order;
import com.multiwarehouse.app.order.service.domain.exception.OrderNotFoundException;
import com.multiwarehouse.app.order.service.domain.mapper.OrderDataMapper;
import com.multiwarehouse.app.order.service.domain.ports.output.repository.OrderRepository;
import com.multiwarehouse.app.order.service.domain.valueobject.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class OrderTrackCommandHandler {
    private final OrderDataMapper orderDataMapper;

    private final OrderRepository orderRepository;

    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> orderResult =
                orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if(orderResult.isEmpty()) {
            log.warn("Couldn't find order with tracking id: {} ", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Couldn't find order with tracking id: {} " +
                    trackOrderQuery.getOrderTrackingId());
        }
        return  orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
