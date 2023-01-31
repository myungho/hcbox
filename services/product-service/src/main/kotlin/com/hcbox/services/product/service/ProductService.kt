package com.hcbox.services.product.service

import com.hcbox.api.dto.PageQueryDto
import com.hcbox.api.dto.ProductDto
import com.hcbox.services.product.mapper.ProductMapper
import com.hcbox.services.product.repository.ProductRepository
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.domain.Page
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
                .switchIfEmpty(Mono.error(NotFoundException("Product Entity Not Found. id=$id")))
                .map { entity ->
                    entity.seasonType = productUpsertDto.seasonType!!
                    entity.name = productUpsertDto.name!!
                    entity.typeCode = productUpsertDto.typeCode!!
                    entity.price = productUpsertDto.price
                    productRepository.save(entity)
                }
                .onErrorMap { DuplicateKeyException("Duplicated product, $productUpsertDto") }
                .map { e -> mapper.toDto(e) }
                .subscribeOn(Schedulers.boundedElastic()).log()
        return dto
    }

    fun retrieve(
        schoolId: Long?,
        seasonType: Integer?,
        name: String?,
        pageQuery: PageQueryDto
    ): Mono<Page<ProductDto.ProductReadDto>> {
        return Mono.fromCallable {
            productRepository.findAllByOptions(schoolId, seasonType, name, pageQuery.of())
        }
            .map { page -> page }
            .subscribeOn(Schedulers.boundedElastic()).log()
    }
}
