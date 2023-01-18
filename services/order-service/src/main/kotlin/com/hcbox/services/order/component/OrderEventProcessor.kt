package com.hcbox.services.order.component

import com.hcbox.api.dto.kafka.OrderEvent
import com.hcbox.api.message.Event
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component("orderEventProcessor")
class OrderEventProcessor : Consumer<Event<Long, OrderEvent>> {

    override fun accept(message: Event<Long, OrderEvent>) {
        println(message)
    }
}
