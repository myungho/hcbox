package com.hcbox.services.view.service

import com.hcbox.api.dto.kafka.OrderEvent
import com.sksamuel.avro4k.Avro
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.stereotype.Service

@Service
class CompositeOrderService(private val streamBridge: StreamBridge) {

    fun create(orderEvent: OrderEvent) {
        val avroRecord = Avro.default.toRecord(OrderEvent.serializer(), orderEvent)
        streamBridge.send("orderEventProcessor-out-0", avroRecord)
    }
}
