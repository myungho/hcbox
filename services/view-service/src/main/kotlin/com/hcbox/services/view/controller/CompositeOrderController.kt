package com.hcbox.services.view.controller

import com.hcbox.api.dto.OrderDto
import com.hcbox.services.view.controller.operation.CompositeOrderOperation
import com.hcbox.services.view.service.CompositeOrderService
import org.springframework.web.bind.annotation.RestController

@RestController
class CompositeOrderController(
    private val compositeOrderService: CompositeOrderService
) : CompositeOrderOperation {

    override fun create(orderUpsertDto: OrderDto.OrderUpsertDto) {
        compositeOrderService.create(orderUpsertDto);
    }
}
