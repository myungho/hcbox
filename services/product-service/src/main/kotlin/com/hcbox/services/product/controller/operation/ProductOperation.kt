package com.hcbox.services.product.controller.operation

import com.hcbox.api.dto.PageQueryDto
import com.hcbox.api.dto.ProductDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@RequestMapping("/products/product-mgmt")
@Tag(name = "Products", description = "Products")
interface ProductOperation {
    @PostMapping
    @Operation(summary = "생성")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201",
            description = "CREATED",
            content = [Content(schema = Schema(implementation = ProductDto.ProductReadDto::class))]
        )]
    )
    fun create(
        @RequestBody productUpsertDto: @Valid ProductDto.ProductUpsertDto
    ): Mono<ProductDto.ProductReadDto>

    @GetMapping("/{id}")
    @Operation(summary = "조회")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun read(
        @Parameter(
            description = "id",
            `in` = ParameterIn.PATH,
            required = true
        ) @PathVariable id: Long,
    ): Mono<ProductDto.ProductReadDto>

    @PutMapping("/{id}")
    @Operation(summary = "업데이트")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun update(
        @Parameter(
            description = "id",
            `in` = ParameterIn.PATH,
            required = true
        ) @PathVariable id: Long,
        @RequestBody productUpsertDto: @Valid ProductDto.ProductUpsertDto
    ): Mono<ProductDto.ProductReadDto>

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

    @GetMapping("/retrieve")
    @Operation(summary = "페이징 조회")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun retrieve(
        @Parameter(description = "학교 ID") @RequestParam(
            value = "schoolId",
            required = false
        ) schoolId: Long?,
        @Parameter(description = "시즌 타입") @RequestParam(
            value = "seasonType",
            required = false
        ) seasonType: Integer?,
        @Parameter(description = "이름") @RequestParam(
            value = "name",
            required = false
        ) name: String?,
        @Parameter(
            schema = Schema(implementation = PageQueryDto::class),
            `in` = ParameterIn.QUERY
        ) pageQuery: PageQueryDto
    ): Mono<Page<ProductDto.ProductReadDto>>
}
