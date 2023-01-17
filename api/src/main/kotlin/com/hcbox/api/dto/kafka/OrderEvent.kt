package com.hcbox.api.dto.kafka

import com.hcbox.api.dto.kafka.serializer.DateSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class OrderEvent(
    val id: Long?,
    val memberId: Long,
    val couponId: Long?,
    val statusCode: String,
    @Serializable(DateSerializer::class)
    val orderDate: Date,
    val phone: String,
    val address: String
)
