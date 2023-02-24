package com.hcbox.services.view.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.hcbox.api.dto.OrderDto
import com.hcbox.api.dto.ProductDto
import com.hcbox.api.dto.composite.CompositeOrderDto
import com.hcbox.api.dto.kafka.OrderEvent
import com.hcbox.common.constant.HcboxConstant
import com.hcbox.common.webclient.WebClientUtil
import com.hcbox.services.view.config.AppConfig
import com.hcbox.services.view.mapper.OrderMapper
import com.sksamuel.avro4k.Avro
import org.apache.avro.generic.GenericRecord
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.function.Tuples
import java.util.stream.Collectors

@Service
class CompositeOrderService(
    private val streamBridge: StreamBridge,
    private val orderMapper: OrderMapper,
    private val webClientUtil: WebClientUtil,
    private val appConfig: AppConfig,
    private val objectMapper: ObjectMapper
) {
    companion object {
        private val log = LoggerFactory.getLogger(CompositeOrderService::class.java)
    }

    fun create(orderUpsertDto: OrderDto.OrderCreateDto) {
        val orderEvent = orderMapper.toEvent(orderUpsertDto)
        orderEvent.eventType = HcboxConstant.EVENT_TYPE_CREATE
        val record = Avro.default.toRecord(OrderEvent.serializer(), orderEvent)
        val message: Message<GenericRecord> = MessageBuilder.withPayload(record).build()
        streamBridge.send("orderEventProcessor-out-0", message)
    }

    //    fun readById(id: Long): Mono<CompositeOrderDto.CompositeOrderReadAllDto> {
//        return ReactiveSecurityContextHolder.getContext()
//            .map { securityContext: SecurityContext ->
//                securityContext.authentication.principal as Jwt
//            }.flatMap {
//                val headers = hashMapOf("Authorization" to "Bearer ${it.tokenValue}")
//
//                webClientUtil.get(OrderDto.OrderReadAllDto::class.java, appConfig.service.order.baseUrl!!, "/$id", headers)
//                    .flatMap {
//                        val productDtoList: MutableList<Mono<ProductDto.ProductReadDto>> = it.orderDetailList?.stream()?.map {
//                            val dto = webClientUtil.get(ProductDto.ProductReadDto::class.java, appConfig.service.product.baseUrl!!, "/${it.productId}")
//                            dto
//                        }!!.collect(Collectors.toList())
//
//                        val fluxProductDtoList: Flux<ProductDto.ProductReadDto> = Flux.concat(productDtoList)
//                        fluxProductDtoList.zipWith(Mono.just(it))
//                        Mono.zip(
//                            {
//                                values ->
//                                val productDtoList: List<ProductDto.ProductReadDto> =
//                                    objectMapper.convertValue(
//                                        values[1],
//                                        object : TypeReference<List<ProductDto.ProductReadDto>>() {})
//
//                                CompositeOrderDto.CompositeOrderReadAllDto(values[0] as OrderDto.OrderReadDto,  productDtoList )
//                            },
//                            Mono.just(it),
//                            fluxProductDtoList
//                        )
//                    }
//
//
//            }
//    }
    fun readById2(id: Long): Mono<CompositeOrderDto.CompositeOrderReadAllDto> {
        return ReactiveSecurityContextHolder.getContext()
            .map { securityContext: SecurityContext ->
                securityContext.authentication.principal as Jwt
            }
            .map {
                val headers = hashMapOf("Authorization" to "Bearer ${it.tokenValue}")
                val userDto = webClientUtil.get(
                    OrderDto.OrderReadAllDto::class.java,
                    appConfig.service.order.baseUrl!!,
                    "/order-request/$id",
                    headers
                )
                Tuples.of(headers, userDto)
            }.flatMap {
                val header = it.t1;
                val productList: Mono<Flux<ProductDto.ProductReadDto>> = it.t2.map {
                    val productList = it.orderDetailList?.stream()?.map {
                        webClientUtil.get(
                            ProductDto.ProductReadDto::class.java,
                            appConfig.service.product.baseUrl!!,
                            "/product-mgmt/${it.productId}",
                            header
                        )
                    }!!.collect(Collectors.toList())
                }


                val mono: Mono<CompositeOrderDto.CompositeOrderReadAllDto> = Mono.zip(
                    { values ->
                        val productDtoList: List<ProductDto.ProductReadDto> =
                            objectMapper.convertValue(
                                values[1],
                                object : TypeReference<List<ProductDto.ProductReadDto>>() {})
                        val orderReadAllDto = values[0] as OrderDto.OrderReadAllDto
                        val orderDto = orderMapper.toDto(orderReadAllDto)
                        CompositeOrderDto.CompositeOrderReadAllDto(
                            orderDto,
                            productDtoList
                        )
                    },
                    it.t2,
                    productList
                )
                mono
            }
    }

    fun readById(id: Long): Mono<CompositeOrderDto.CompositeOrderReadAllDto> {
        return Mono.just(CompositeOrderDto.CompositeOrderReadAllDto())
    }

    fun getJwt(): Mono<Jwt> {
        return ReactiveSecurityContextHolder.getContext()
            .map { securityContext: SecurityContext ->
                securityContext.authentication.principal as Jwt
            }
    }
}
