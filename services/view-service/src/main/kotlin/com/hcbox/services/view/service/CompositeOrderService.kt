package com.hcbox.services.view.service

import com.hcbox.api.dto.OrderDto
import com.hcbox.api.dto.ProductDto
import com.hcbox.api.dto.composite.CompositeOrderDto
import com.hcbox.api.dto.kafka.OrderEvent
import com.hcbox.common.webclient.WebClientUtil
import com.hcbox.services.view.config.AppConfig
import com.hcbox.services.view.mapper.OrderMapper
import com.sksamuel.avro4k.Avro
import org.apache.avro.generic.GenericRecord
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CompositeOrderService(
    private val streamBridge: StreamBridge,
    private val orderMapper: OrderMapper,
    private val webClientUtil: WebClientUtil,
    private val appConfig: AppConfig
) {

    fun create(orderUpsertDto: OrderDto.OrderUpsertDto) {
        val orderEvent = orderMapper.toEvent(orderUpsertDto)
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
