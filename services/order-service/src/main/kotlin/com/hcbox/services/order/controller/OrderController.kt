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

    override fun create(OrderUpsertDto: OrderDto.OrderUpsertDto): Mono<OrderDto.OrderReadDto> {
        return orderService.create(OrderUpsertDto);
    }

    override fun read(id: Long): Mono<OrderDto.OrderReadDto> {
        val Order: Mono<OrderDto.OrderReadDto> = orderService.findById(id);
        return Order;
    }

    override fun update(
        id: Long,
        orderUpsertDto: OrderDto.OrderUpsertDto
    ): Mono<OrderDto.OrderReadDto> {
        return orderService.update(id, orderUpsertDto)
    }

    override fun delete(id: Long): Mono<Void> {
        return orderService.delete(id)
    }
}
