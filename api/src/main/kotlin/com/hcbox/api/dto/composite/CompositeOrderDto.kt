package com.hcbox.api.dto.composite

import com.hcbox.api.dto.OrderDto
import com.hcbox.api.dto.ProductDto

class CompositeOrderDto {
    class CompositeOrderReadAllDto (
        var order: OrderDto.OrderReadDto? = null,
        var productList: List<ProductDto.ProductReadDto>? = null,
        var totalPrice: Long? = null
    )
}
