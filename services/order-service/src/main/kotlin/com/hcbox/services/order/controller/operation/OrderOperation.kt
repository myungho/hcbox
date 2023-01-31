package com.hcbox.services.order.controller.operation

import com.hcbox.api.dto.OrderDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@RequestMapping("/orders/order-request")
@Tag(name = "Orders", description = "Orders")
interface OrderOperation {
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
    ): Mono<OrderDto.OrderReadDto>

    @GetMapping("/{id}")
    @Operation(summary = "조회")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun read(
        @Parameter(
            description = "id",
            `in` = ParameterIn.PATH,
            required = true
        ) @PathVariable id: Long,
    ): Mono<OrderDto.OrderReadAllDto>

    @PutMapping("/{id}")
    @Operation(summary = "업데이트")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun update(
        @Parameter(
            description = "id",
            `in` = ParameterIn.PATH,
            required = true
        ) @PathVariable id: Long,
        @RequestBody orderUpsertDto: @Valid OrderDto.OrderUpsertDto
    ): Mono<OrderDto.OrderReadAllDto>

    @DeleteMapping("/{id}")
    @Operation(summary = "삭제")
    @ApiResponses(value = [ApiResponse(responseCode = "204", description = "No Content")])
    fun delete(
        @Parameter(
            description = "id",
            `in` = ParameterIn.PATH,
            required = true
        ) @PathVariable id: Long,
    ): Mono<Void>
}
