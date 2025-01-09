package com.multiwarehouse.app.customer.service.domain.ports.output.message.publisher;

import com.multiwarehouse.app.customer.service.domain.event.CustomerCreatedEvent;

public interface CustomerMessagePublisher {

    void publish(CustomerCreatedEvent customerCreatedEvent);

}
