package com.hcbox.api.dto

import com.sksamuel.avro4k.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.util.*

class OrderDto {
    @Serializable
    data class OrderUpsertDto(
        var memberId: Long,
        var couponId: Long,
        var stock: Long,
        @Serializable(with = LocalDateSerializer::class)
        var orderDate: Date? = Date(),
//        var orderDate: LocalDate = LocalDate.now(),
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
