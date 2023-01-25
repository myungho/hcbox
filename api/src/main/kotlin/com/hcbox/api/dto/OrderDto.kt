package com.hcbox.api.dto

import java.util.*

class OrderDto {
    data class OrderUpsertDto(
        var studentName: String,
        var orderDate: Date = Date(),
        var phone: String,
        var address: String?,
        var schoolId: Long,
    )
    data class OrderReadDto(
        var id: Long,
        var studentName: String,
        var statusCode: Long,
        var orderDate: Date,
        var phone: String,
        var address: String?,
        var school: SchoolDto.SchoolReadDto,
    )
}
