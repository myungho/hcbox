package com.hcbox.services.view.mapper

import com.hcbox.api.dto.OrderDto
import com.hcbox.api.dto.kafka.OrderEvent
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface OrderMapper {
    fun toEvent(dto: OrderDto.OrderCreateDto): OrderEvent
    fun toDto(dto: OrderDto.OrderReadAllDto): OrderDto.OrderReadDto
}
