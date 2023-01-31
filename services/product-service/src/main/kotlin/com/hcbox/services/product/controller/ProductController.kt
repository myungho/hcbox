package com.hcbox.services.product.controller

import com.hcbox.api.dto.PageQueryDto
import com.hcbox.api.dto.ProductDto
import com.hcbox.services.product.controller.operation.ProductOperation
import com.hcbox.services.product.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class ProductController(
    private val productService: ProductService
) : ProductOperation {
    override fun create(productUpsertDto: ProductDto.ProductUpsertDto): Mono<ProductDto.ProductReadDto> {
        return productService.create(productUpsertDto);
    }

    override fun read(id: Long): Mono<ProductDto.ProductReadDto> {
        val product: Mono<ProductDto.ProductReadDto> = productService.findById(id);
        return product;
    }

    override fun update(
        id: Long,
        productUpsertDto: ProductDto.ProductUpsertDto
    ): Mono<ProductDto.ProductReadDto> {
        return productService.update(id, productUpsertDto)
    }

    override fun delete(id: Long): Mono<Void> {
        return productService.delete(id)
    }

    override fun retrieve(
        schoolId: Long?,
        seasonType: Integer?,
        name: String?,
        pageQuery: PageQueryDto
    ): Mono<Page<ProductDto.ProductReadDto>> {
        return productService.retrieve(schoolId, seasonType, name, pageQuery)
    }
}
