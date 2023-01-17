package com.hcbox.services.order.config

import com.hcbox.api.dto.kafka.OrderEvent
import com.hcbox.api.message.Event
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer


@Configuration
class ConsumerConfig {
    @Bean
    fun orderConsumer() = Consumer<Event<Long, OrderEvent>> {
        println(it)
    }
}
