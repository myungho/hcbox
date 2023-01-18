package com.hcbox.services.view.controller

import com.hcbox.api.dto.OrderDto
import com.hcbox.api.dto.kafka.OrderEvent
import com.hcbox.services.view.controller.operation.CompositeOrderOperation
import com.hcbox.services.view.mapper.OrderMapper
import com.hcbox.services.view.service.CompositeOrderService
import org.springframework.web.bind.annotation.RestController

@RestController
class CompositeOrderController(
    private val compositeOrderService: CompositeOrderService,
    private val orderMapper: OrderMapper
) : CompositeOrderOperation {

    override fun create(orderUpsertDto: OrderDto.OrderUpsertDto) {
        val orderEvent = orderMapper.toEvent(orderUpsertDto)
        compositeOrderService.create(orderEvent);
    }
}
