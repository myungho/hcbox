package com.hcbox.services.product.mapper

import com.hcbox.api.dto.ProductDto
import com.hcbox.services.product.dao.ProductEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ProductMapper {
    fun toDto(entity: ProductEntity): ProductDto.ProductReadDto
    fun toEntity(dto: ProductDto.ProductUpsertDto): ProductEntity
}
