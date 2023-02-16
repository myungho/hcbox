package com.hcbox.services.order.controller.operation

import com.hcbox.api.dto.OrderDto
import com.hcbox.api.dto.PageQueryDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@RequestMapping("/order-request")
@Tag(name = "Orders", description = "Orders")
interface OrderOperation {
    @PostMapping
    @Operation(summary = "생성", security = [SecurityRequirement(name = "bearerAuth")])
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
    @Operation(summary = "조회", security = [SecurityRequirement(name = "bearerAuth")])
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun read(
        @Parameter(
            description = "id",
            `in` = ParameterIn.PATH,
            required = true
        ) @PathVariable id: Long,
    ): Mono<OrderDto.OrderReadAllDto>

    @PutMapping("/{id}")
    @Operation(summary = "업데이트", security = [SecurityRequirement(name = "bearerAuth")])
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
    @Operation(summary = "삭제", security = [SecurityRequirement(name = "bearerAuth")])
    @ApiResponses(value = [ApiResponse(responseCode = "204", description = "No Content")])
    fun delete(
        @Parameter(
            description = "id",
            `in` = ParameterIn.PATH,
            required = true
        ) @PathVariable id: Long,
    ): Mono<Void>

    @GetMapping("/retrieve")
    @Operation(summary = "페이징 조회", security = [SecurityRequirement(name = "bearerAuth")])
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun retrieve(
        @Parameter(description = "학교 ID") @RequestParam(
            value = "studentName",
            required = false
        ) studentName: String?,
        @Parameter(description = "시즌 타입") @RequestParam(
            value = "statusCode",
            required = false
        ) statusCode: String?,
        @Parameter(description = "이름") @RequestParam(
            value = "schoolId",
            required = false
        ) schoolId: Long?,
        @Parameter(
            schema = Schema(implementation = PageQueryDto::class),
            `in` = ParameterIn.QUERY
        ) pageQuery: PageQueryDto
    ): Mono<Page<OrderDto.OrderReadDto>>
}
