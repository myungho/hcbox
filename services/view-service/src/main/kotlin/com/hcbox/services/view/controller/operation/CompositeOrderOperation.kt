package com.hcbox.services.view.controller.operation

import com.hcbox.api.dto.OrderDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@RequestMapping("/view/orders")
@Tag(name = "Orders", description = "Orders")
interface CompositeOrderOperation {
    @PostMapping
    @Operation(summary = "생성")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201",
            description = "CREATED",
            content = [Content(schema = Schema(implementation = OrderDto.OrderReadDto::class))]
        )]
    )
    fun create(
        @RequestBody orderUpsertDto: @Valid OrderDto.OrderUpsertDto
    )
}
