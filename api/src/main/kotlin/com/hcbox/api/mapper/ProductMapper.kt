package com.hcbox.api.mapper

import com.hcbox.api.dto.ProductDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ProductMapper {
    fun toDto(entity: ProductEntity): ProductDto.ProductReadDto
    fun toEntity(dto: ProductDto.ProductUpsertDto): ProductEntity
}
