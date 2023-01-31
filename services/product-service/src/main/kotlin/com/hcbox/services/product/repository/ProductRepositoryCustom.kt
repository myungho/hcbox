package com.hcbox.services.product.repository

import com.hcbox.api.dto.ProductDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
interface ProductRepositoryCustom {
    fun findAllByOptions(
        schoolId: Long?, seasonType: Integer?, name: String?, pageRequest: PageRequest
    ): Page<ProductDto.ProductReadDto>
}
