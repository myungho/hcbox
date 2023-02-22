package com.hcbox.services.view.service

import com.hcbox.api.dto.OrderDto
import com.hcbox.api.dto.kafka.OrderEvent
import com.hcbox.common.constant.HcboxConstant
import com.hcbox.services.view.mapper.OrderMapper
import com.sksamuel.avro4k.Avro
import org.apache.avro.generic.GenericRecord
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class CompositeOrderService(
    private val streamBridge: StreamBridge,
    private val orderMapper: OrderMapper,
) {

    companion object {
        private val log = LoggerFactory.getLogger(CompositeOrderService::class.java)
    }

    fun create(orderUpsertDto: OrderDto.OrderCreateDto) {
        log.info("@@@ TEST")
        val orderEvent = orderMapper.toEvent(orderUpsertDto)
        orderEvent.eventType = HcboxConstant.EVENT_TYPE_CREATE
        val record = Avro.default.toRecord(OrderEvent.serializer(), orderEvent)
        val message: Message<GenericRecord> = MessageBuilder.withPayload(record).build()
        streamBridge.send("orderEventProcessor-out-0", message)
    }

//    fun readById(id: Long): Mono<CompositeOrderDto.CompositeOrderReadAllDto> {
//        val orderReadAllDto =
//            webClientUtil[OrderDto.OrderReadAllDto::class.java, appConfig.service.order.baseUrl!!, "/orders/$id", null].block()
//
//        val productListDto =
//            webClientUtil[Any::class.java, appConfig.service.product.baseUrl!!, "/products?orderId=$id", null] as List<ProductDto.ProductReadDto>
//
//        CompositeOrderDto.CompositeOrderReadAllDto(orderReadDto, productListDto)
//
//
//    }
}
