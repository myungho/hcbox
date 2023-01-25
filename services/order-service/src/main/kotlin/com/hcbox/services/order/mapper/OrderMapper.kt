package com.hcbox.services.order.mapper

import com.hcbox.api.dto.OrderDto
import com.hcbox.services.order.dao.OrderEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface OrderMapper {
    @Mapping(source = "schoolEntity", target = "school")
    fun toDto(entity: OrderEntity): OrderDto.OrderReadDto
    fun toEntity(dto: OrderDto.OrderUpsertDto): OrderEntity
}
