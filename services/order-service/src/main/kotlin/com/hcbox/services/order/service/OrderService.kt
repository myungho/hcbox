package com.hcbox.services.order.service

import com.hcbox.api.dto.OrderDto
import com.hcbox.common.constant.StatusConstant
import com.hcbox.common.http.NotFoundException
import com.hcbox.services.order.mapper.OrderMapper
import com.hcbox.services.order.repository.OrderRepository
import com.hcbox.services.order.repository.SchoolRepository
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val schoolRepository: SchoolRepository,
    private val mapper: OrderMapper,
) {
    fun create(order: OrderDto.OrderUpsertDto): Mono<OrderDto.OrderReadDto> {
        return Mono.fromCallable {
            val schoolEntity = schoolRepository.findById(order.schoolId)
                .orElseThrow { NotFoundException("School Entity Not found. id=${order.schoolId}") }
            val orderEntity = mapper.toEntity(order)
            orderEntity.schoolEntity = schoolEntity
            orderRepository.save(orderEntity)
        }
            .map { entity -> mapper.toDto(entity) }
            .subscribeOn(Schedulers.boundedElastic()).log()
    }

    fun findById(id: Long): Mono<OrderDto.OrderReadDto> {
        return Mono.fromCallable { orderRepository.findById(id).orElse(null) }
            .switchIfEmpty(Mono.error(NotFoundException("Entity Not Found. id=$id")))
            .map { entity -> mapper.toDto(entity) }
            .subscribeOn(Schedulers.boundedElastic()).log()
    }

    fun delete(id: Long): Mono<Void> {
        return Mono.fromCallable { orderRepository.findById(id).orElse(null) }
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
                .switchIfEmpty(Mono.error(NotFoundException("Order Entity Not Found. id=$id")))
                .map { entity ->
                    val schoolEntity = schoolRepository.findById(orderUpsertDto.schoolId)
                        .orElseThrow { NotFoundException("School Entity Not Found. id=${orderUpsertDto.schoolId}") }
                    entity.studentName = orderUpsertDto.studentName
                    entity.statusCode = StatusConstant.ORDER_STATUS_CODE_RECEIPT
                    entity.orderDate = orderUpsertDto.orderDate
                    entity.phone = orderUpsertDto.phone
                    entity.address = orderUpsertDto.address
                    entity.schoolEntity = schoolEntity

                    orderRepository.save(entity)
                }
                .onErrorMap { DuplicateKeyException("Duplicated Order, $orderUpsertDto") }
                .map { e -> mapper.toDto(e) }
                .subscribeOn(Schedulers.boundedElastic()).log()
        return dto
    }
}
