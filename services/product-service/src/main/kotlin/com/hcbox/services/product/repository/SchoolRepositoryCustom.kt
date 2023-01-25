package com.hcbox.services.product.repository

import com.hcbox.api.dto.SchoolDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
interface SchoolRepositoryCustom {
    fun findAllByOptions(
        name: String?, pageRequest: PageRequest
    ): Page<SchoolDto.SchoolReadDto>
}
