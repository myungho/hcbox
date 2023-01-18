package com.hcbox.api.dto

import java.util.*

class OrderDto {
    data class OrderUpsertDto(
        var memberId: Long,
        var couponId: Long,
        var stock: Long,
        var orderDate: Date? = Date(),
        var phone: String?,
        var address: String,
    )
    data class OrderReadDto(
        var id: Long,
        var memberId: String,
        var couponId: Long,
        var stcd: Long,
        var stock: Long,
        var orderDate: Date?,
        var phone: String?,
        var address: String?,
    )
}
