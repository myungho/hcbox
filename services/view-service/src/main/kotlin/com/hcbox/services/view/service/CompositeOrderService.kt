package com.hcbox.services.view.service

import com.hcbox.api.dto.OrderDto
import com.hcbox.api.dto.kafka.OrderEvent
import com.hcbox.services.view.mapper.OrderMapper
import com.sksamuel.avro4k.Avro
import org.apache.avro.generic.GenericRecord
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class CompositeOrderService(
    private val streamBridge: StreamBridge,
    private val orderMapper: OrderMapper,
) {

    fun create(orderUpsertDto: OrderDto.OrderUpsertDto) {
        val orderEvent = orderMapper.toEvent(orderUpsertDto)
        val record = Avro.default.toRecord(OrderEvent.serializer(), orderEvent)
        val message: Message<GenericRecord> = MessageBuilder.withPayload(record).build()
        streamBridge.send("orderEventProcessor-out-0", message)
    }
}
