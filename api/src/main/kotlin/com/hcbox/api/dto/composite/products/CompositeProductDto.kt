package com.hcbox.api.dto.composite.products

import com.hcbox.api.dto.ProductDto
import com.hcbox.api.dto.SchoolDto

class CompositeProductDto {
    class CompositeProductReadDto{
        var schoolDto: SchoolDto.SchoolReadDto? = null
        var productDtoList: List<ProductDto.ProductReadDto>? = null
    }
}
