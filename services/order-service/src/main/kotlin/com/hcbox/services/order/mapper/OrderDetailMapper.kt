package com.hcbox.services.order.mapper

import com.hcbox.api.dto.OrderDetailDto
import com.hcbox.services.order.dao.OrderDetailEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface OrderDetailMapper {
    fun toEntity(dto: OrderDetailDto.OrderDetailUpsertDto): OrderDetailEntity
}
