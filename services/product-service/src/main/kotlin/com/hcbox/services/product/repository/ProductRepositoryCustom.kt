package com.hcbox.services.product.repository

import com.hcbox.api.dto.ProductDto
import com.hcbox.services.product.dao.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
interface ProductRepositoryCustom {
    fun findAllByOptions(
        schoolId: Long?, seasonType: Int?, name: String?, pageRequest: PageRequest
    ): Page<ProductDto.ProductReadDto>

    fun findBySchoolIdAndGenderAndSeasonType(id: Long, gender: Int?, seasonType: Int?): List<ProductDto.ProductReadDto>
}
