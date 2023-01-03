package com.hcbox.api.service

import com.hcbox.api.dto.ProductDto
import com.hcbox.api.mapper.ProductMapper
import com.hcbox.api.repository.ProductRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val mapper: ProductMapper,
) {
    fun create(muscleCreateDto: ProductDto.ProductUpsertDto): ProductDto.ProductReadDto {
        val muscleEntity = mapper.toEntity(muscleCreateDto)
        return mapper.toDto(productRepository.save(muscleEntity))
    }

    fun findById(id: Long): ProductDto.ProductReadDto {
        val muscleEntity =
            productRepository.findById(id)?.orElseThrow { EntityNotFoundException("Notfound") }!!
        return mapper.toDto(muscleEntity)
    }

    fun delete(id: Long) {
        productRepository.deleteById(id)
    }

    fun update(id: Long, productUpsertDto: ProductDto.ProductUpsertDto): ProductDto.ProductReadDto {
        val muscleEntity =
            productRepository.findById(id)?.orElseThrow { EntityNotFoundException("Not found") }!!
        productUpsertDto.map(muscleEntity)
        return mapper.toDto(productRepository.save(muscleEntity))
    }
}
