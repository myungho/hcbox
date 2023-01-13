package com.hcbox.services.order.service

import com.hcbox.api.dto.OrderDto
import com.hcbox.common.constant.StcdConstant
import com.hcbox.services.order.mapper.OrderMapper
import com.hcbox.services.order.repository.OrderRepository
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.webjars.NotFoundException
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val mapper: OrderMapper,
) {
    fun create(order: OrderDto.OrderUpsertDto): Mono<OrderDto.OrderReadDto> {
        return Mono.fromCallable { orderRepository.save(mapper.toEntity(order)) }
            .map { entity -> mapper.toDto(entity) }
            .subscribeOn(Schedulers.boundedElastic()).log()
    }

    fun findById(id: Long): Mono<OrderDto.OrderReadDto> {
        return Mono.fromCallable { orderRepository.findById(id) }
            .map { entity -> entity.get() }
            .switchIfEmpty(Mono.error(NotFoundException("Entity Not Found. id=$id")))
            .map { entity -> mapper.toDto(entity) }
            .subscribeOn(Schedulers.boundedElastic()).log()
    }

    fun delete(id: Long): Mono<Void> {
        return Mono.fromCallable { orderRepository.findById(id) }
            .map { entity -> entity.get() }
            .switchIfEmpty(Mono.error(NotFoundException("Entity Not Found. id=$id")))
            .map { entity -> orderRepository.delete(entity) }
            .subscribeOn(Schedulers.boundedElastic()).log().then()
    }

    fun update(
        id: Long,
        orderUpsertDto: OrderDto.OrderUpsertDto
    ): Mono<OrderDto.OrderReadDto> {
        val dto =
            Mono.fromCallable { orderRepository.findById(id) }
                .map { entity -> entity.get() }
                .switchIfEmpty(Mono.error(NotFoundException("Entity Not Found. id=$id")))
                .map { entity ->
                    entity.memberId = orderUpsertDto.memberId
                    entity.couponId = orderUpsertDto.couponId
                    entity.statusCode = StcdConstant.ORDER_STATUS_CODE_RECEIPT
                    entity.orderDate = orderUpsertDto.orderDate
                    entity.phone = orderUpsertDto.phone
                    entity.address = orderUpsertDto.address
                    orderRepository.save(entity)
                }
                .onErrorMap { DuplicateKeyException("Duplicated Order, $orderUpsertDto") }
                .map { e -> mapper.toDto(e) }
                .subscribeOn(Schedulers.boundedElastic()).log()
        return dto
    }
}
