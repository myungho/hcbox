package com.hcbox.services.order.repository

import com.hcbox.api.dto.OrderDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
interface OrderRepositoryCustom {
    fun findAllByOptions(
        studentName: String?,
        statusCode: String?,
        schoolId: Long?,
        of: PageRequest
    ): Page<OrderDto.OrderReadDto>
}
