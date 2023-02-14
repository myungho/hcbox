package com.hcbox.services.view.controller

import com.hcbox.api.dto.composite.products.CompositeProductDto
import com.hcbox.services.view.controller.operation.CompositeProductOperation
import com.hcbox.services.view.service.CompositeProductService
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class CompositeProductController(
    private val compositeProductService: CompositeProductService
) : CompositeProductOperation {
    override fun retrieve(
        id: Long, gender: Int?, seasonType: Int?
    ): Mono<CompositeProductDto.CompositeProductReadDto> {
        return compositeProductService.retrieve(id, gender, seasonType)
    }
}
