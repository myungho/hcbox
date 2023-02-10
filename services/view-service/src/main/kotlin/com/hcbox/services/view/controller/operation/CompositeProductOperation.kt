package com.hcbox.services.view.controller.operation

import com.hcbox.api.dto.composite.products.CompositeProductDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RequestMapping("/products")
@Tag(name = "Products", description = "Products")
interface CompositeProductOperation {
    @GetMapping("/schools/{id}/retrieve")
    @Operation(summary = "상품 조회", security = [SecurityRequirement(name = "bearerAuth")])
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "Ok")])
    fun retrieve(
        @Parameter(
            description = "id",
            `in` = ParameterIn.PATH,
            required = true
        ) @PathVariable id: Long
    ): Mono<CompositeProductDto.CompositeProductReadDto>
}
