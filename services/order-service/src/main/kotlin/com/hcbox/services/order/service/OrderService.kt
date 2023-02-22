package com.hcbox.services.order.service

import com.hcbox.api.dto.OrderDto
import com.hcbox.api.dto.PageQueryDto
import com.hcbox.common.constant.HcboxConstant
import com.hcbox.common.http.NotFoundException
import com.hcbox.services.order.mapper.OrderDetailMapper
import com.hcbox.services.order.mapper.OrderMapper
import com.hcbox.services.order.repository.OrderDetailRepository
import com.hcbox.services.order.repository.OrderRepository
import com.hcbox.services.order.repository.SchoolRepository
import org.slf4j.LoggerFactory
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderDetailRepository: OrderDetailRepository,
    private val schoolRepository: SchoolRepository,
    private val orderMapper: OrderMapper,
    private val orderDetailMapper: OrderDetailMapper,
) {
    companion object OrderService {
        private val log = LoggerFactory.getLogger(OrderService::class.java)
    }

    @Transactional
    fun create(orderUpsertDto: OrderDto.OrderCreateDto): Mono<OrderDto.OrderReadDto> {
        return Mono.fromCallable {
            val schoolEntity = schoolRepository.findById(orderUpsertDto.schoolId!!)
                .orElseThrow { NotFoundException("School Entity Not found. id=${orderUpsertDto.schoolId}") }
            val orderEntity = orderMapper.toEntity(orderUpsertDto)
            orderEntity.statusCode = HcboxConstant.ORDER_STATUS_CODE_RECEIPT
            orderEntity.schoolEntity = schoolEntity
            val savedOrderEntity = orderRepository.save(orderEntity)
            orderUpsertDto.orderDetailList?.forEach { orderDetail ->
                val orderDetailEntity = orderDetailMapper.toEntity(orderDetail)
                orderDetailEntity.orderEntity = savedOrderEntity
                orderDetailRepository.save(orderDetailEntity)
            }
            orderMapper.toDto(savedOrderEntity)
        }
            .subscribeOn(Schedulers.boundedElastic()).log()
    }

    fun findById(id: Long): Mono<OrderDto.OrderReadAllDto> {
        return Mono.fromCallable { orderRepository.findById(id).orElse(null) }
            .switchIfEmpty(Mono.error(NotFoundException("Entity Not Found. id=$id")))
            .map { entity -> orderMapper.toAllDto(entity) }
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
        orderUpsertDto: OrderDto.OrderUpdateDto
    ): Mono<OrderDto.OrderReadAllDto> {
        val dto =
            Mono.fromCallable { orderRepository.findById(id) }
                .map { entity -> entity.get() }
                .switchIfEmpty(Mono.error(NotFoundException("Order Entity Not Found. id=$id")))
                .map { entity ->
                    val schoolEntity = schoolRepository.findById(orderUpsertDto.schoolId!!)
                        .orElseThrow { NotFoundException("School Entity Not Found. id=${orderUpsertDto.schoolId}") }
                    entity.studentName = orderUpsertDto.studentName
                    entity.statusCode = HcboxConstant.ORDER_STATUS_CODE_RECEIPT
                    entity.orderDate = orderUpsertDto.orderDate
                    entity.phone = orderUpsertDto.phone
                    entity.schoolEntity = schoolEntity

                    orderRepository.save(entity)
                }
                .onErrorMap { DuplicateKeyException("Duplicated Order, $orderUpsertDto") }
                .map { e -> orderMapper.toAllDto(e) }
                .subscribeOn(Schedulers.boundedElastic()).log()
        return dto
    }

    fun retrieve(
        studentName: String?,
        statusCode: String?,
        schoolId: Long?,
        pageQuery: PageQueryDto
    ): Mono<Page<OrderDto.OrderReadDto>> {

        return Mono.fromCallable {
            orderRepository.findAllByOptions(studentName, statusCode, schoolId, pageQuery.of())
        }.map { page -> page }.subscribeOn(Schedulers.boundedElastic()).log()
    }
}
