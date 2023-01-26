package com.hcbox.services.order.component

import com.hcbox.api.dto.kafka.OrderEvent
import com.hcbox.services.order.mapper.OrderMapper
import com.hcbox.services.order.service.OrderService
import com.sksamuel.avro4k.Avro
import org.apache.avro.generic.GenericData
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component("orderEventProcessor")
class OrderEventProcessor(
    private val orderService: OrderService,
    private val orderMapper: OrderMapper
) : Consumer<GenericData.Record> {

    companion object {
        private val log = LoggerFactory.getLogger(OrderEventProcessor::class.java)
    }

    override fun accept(record: GenericData.Record) {
        val event = Avro.default.fromRecord(OrderEvent.serializer(), record)
        val upsertDto = orderMapper.toDto(event)
        orderService.create(upsertDto).map { saved -> log.info("saved data. data=$saved") }.subscribe()
    }
}
