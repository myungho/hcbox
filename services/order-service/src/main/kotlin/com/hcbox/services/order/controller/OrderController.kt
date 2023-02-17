package com.hcbox.services.order.controller

import com.hcbox.api.dto.OrderDto
import com.hcbox.api.dto.PageQueryDto
import com.hcbox.services.order.controller.operation.OrderOperation
import com.hcbox.services.order.service.OrderService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class OrderController(
    private val orderService: OrderService
) : OrderOperation {

    override fun create(orderUpsertDto: OrderDto.OrderCreateDto): Mono<OrderDto.OrderReadDto> {
        return orderService.create(orderUpsertDto);
    }

    override fun read(id: Long): Mono<OrderDto.OrderReadAllDto> {
        val order: Mono<OrderDto.OrderReadAllDto> = orderService.findById(id);
        return order;
    }

    override fun update(
        id: Long,
        orderUpsertDto: OrderDto.OrderUpdateDto
    ): Mono<OrderDto.OrderReadAllDto> {
        return orderService.update(id, orderUpsertDto)
    }

    override fun delete(id: Long): Mono<Void> {
        return orderService.delete(id)
    }

    override fun retrieve(
        studentName: String?,
        statusCode: String?,
        schoolId: Long?,
        pageQuery: PageQueryDto
    ): Mono<Page<OrderDto.OrderReadDto>> {
        return orderService.retrieve(studentName, statusCode, schoolId, pageQuery)
    }
}
