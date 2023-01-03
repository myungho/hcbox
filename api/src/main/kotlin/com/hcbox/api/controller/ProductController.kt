package com.hcbox.api.controller

import com.hcbox.api.dto.ProductDto
import com.hcbox.api.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService,
) {
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
    ): ResponseEntity<*> {
        val created = productService.create(productUpsertDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @GetMapping("/{id}")
    @Operation(summary = "조회")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun read(
        @Parameter(description = "id", `in` = ParameterIn.PATH, required = true) @PathVariable id: Long,
    ): ResponseEntity<*> {
        val dto = productService.findById(id)
        return ResponseEntity.ok(dto)
    }

    @PutMapping("/{id}")
    @Operation(summary = "업데이트")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun update(
        @Parameter(description = "id", `in` = ParameterIn.PATH, required = true) @PathVariable id: Long,
        @RequestBody productUpsertDto: @Valid ProductDto.ProductUpsertDto
    ): ResponseEntity<*> {
        val dto = productService.update(id, productUpsertDto)
        return ResponseEntity.ok(dto)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "삭제")
    @ApiResponses(value = [ApiResponse(responseCode = "204", description = "No Content")])
    fun delete(
        @Parameter(description = "id", `in` = ParameterIn.PATH, required = true) @PathVariable id: Long,
    ): ResponseEntity<*> {
        productService.delete(id)
        return ResponseEntity<Any>(HttpStatus.NO_CONTENT)
    }
}
