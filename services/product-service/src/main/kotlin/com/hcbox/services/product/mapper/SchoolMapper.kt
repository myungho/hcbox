package com.hcbox.services.product.mapper

import com.hcbox.api.dto.SchoolDto
import com.hcbox.services.product.dao.SchoolEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface SchoolMapper {
    fun toDto(entity: SchoolEntity): SchoolDto.SchoolReadDto
    fun toEntity(dto: SchoolDto.SchoolUpsertDto): SchoolEntity
}
