package com.hcbox.services.product.service

import com.hcbox.api.dto.ProductDto
import com.hcbox.services.product.mapper.ProductMapper
import com.hcbox.services.product.repository.ProductRepository
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.webjars.NotFoundException
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val mapper: ProductMapper,
) {
    fun create(product: ProductDto.ProductUpsertDto): Mono<ProductDto.ProductReadDto> {
        return Mono.fromCallable { productRepository.save(mapper.toEntity(product)) }
            .map { entity -> mapper.toDto(entity) }
            .subscribeOn(Schedulers.boundedElastic()).log()
    }

    fun findById(id: Long): Mono<ProductDto.ProductReadDto> {
        return Mono.fromCallable { productRepository.findById(id) }
            .map { entity -> entity.get() }
            .switchIfEmpty(Mono.error(NotFoundException("Entity Not Found. id=$id")))
            .map { entity -> mapper.toDto(entity) }
            .subscribeOn(Schedulers.boundedElastic()).log()
    }

    fun delete(id: Long): Mono<Void> {
        return Mono.fromCallable { productRepository.findById(id) }
            .map { entity -> entity.get() }
            .switchIfEmpty(Mono.error(NotFoundException("Entity Not Found. id=$id")))
            .map { entity -> productRepository.delete(entity) }
            .subscribeOn(Schedulers.boundedElastic()).log().then()
    }

    fun update(
        id: Long,
        productUpsertDto: ProductDto.ProductUpsertDto
    ): Mono<ProductDto.ProductReadDto> {
        val dto =
            Mono.fromCallable { productRepository.findById(id) }
                .map { entity -> entity.get() }
                .switchIfEmpty(Mono.error(NotFoundException("Entity Not Found. id=$id")))
                .map { entity ->
                    entity.code = productUpsertDto.code
                    entity.brandCode = productUpsertDto.brandCode
                    entity.detail = productUpsertDto.detail
                    entity.discount = productUpsertDto.discount
                    entity.imagePath1 = productUpsertDto.imagePath1
                    entity.imagePath2 = productUpsertDto.imagePath2
                    entity.imagePath3 = productUpsertDto.imagePath3
                    entity.name = productUpsertDto.name
                    entity.price = productUpsertDto.price
                    entity.stock = productUpsertDto.stock
                    productRepository.save(entity)
                }
                .onErrorMap { DuplicateKeyException("Duplicated product, $productUpsertDto") }
                .map { e -> mapper.toDto(e) }
                .subscribeOn(Schedulers.boundedElastic()).log()
        return dto
    }
}
