package com.hcbox.services.order.controller

import com.hcbox.api.dto.OrderDto
import com.hcbox.services.order.controller.operation.OrderOperation
import com.hcbox.services.order.service.OrderService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class OrderController(
    private val orderService: OrderService
) : OrderOperation {

    override fun create(orderUpsertDto: OrderDto.OrderUpsertDto): Mono<OrderDto.OrderReadDto> {
        return orderService.create(orderUpsertDto);
    }

    override fun read(id: Long): Mono<OrderDto.OrderReadAllDto> {
        val order: Mono<OrderDto.OrderReadAllDto> = orderService.findById(id);
        return order;
    }

    override fun update(
        id: Long,
        orderUpsertDto: OrderDto.OrderUpsertDto
    ): Mono<OrderDto.OrderReadAllDto> {
        return orderService.update(id, orderUpsertDto)
    }

    override fun delete(id: Long): Mono<Void> {
        return orderService.delete(id)
    }
}
