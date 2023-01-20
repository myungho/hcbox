package com.hcbox.services.order.component

import com.hcbox.api.dto.kafka.OrderEvent
import com.sksamuel.avro4k.Avro
import org.apache.avro.generic.GenericData
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component("orderEventProcessor")
class OrderEventProcessor : Consumer<GenericData.Record> {

    override fun accept(record: GenericData.Record) {
        val event = Avro.default.fromRecord(OrderEvent.serializer(), record)
        println(event)
    }
}
