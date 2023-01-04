package com.hcbox.services.product.controller.operation

import com.hcbox.api.dto.ProductDto
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

@RequestMapping("/products")
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
}
