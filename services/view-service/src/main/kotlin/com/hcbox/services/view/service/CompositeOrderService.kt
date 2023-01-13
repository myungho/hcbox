package com.hcbox.services.view.service

import com.hcbox.api.dto.OrderDto
import com.sksamuel.avro4k.Avro
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.stereotype.Service


@Service
class CompositeOrderService(private val streamBridge: StreamBridge) {

    fun create(orderUpsertDto: OrderDto.OrderUpsertDto) {
        val avroRecord = Avro.default.toRecord(OrderDto.OrderUpsertDto.serializer(), orderUpsertDto)
        streamBridge.send("orderCreated", avroRecord)
    }

}
