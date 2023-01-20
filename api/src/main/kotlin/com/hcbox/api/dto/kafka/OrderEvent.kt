package com.hcbox.api.dto.kafka

import com.hcbox.api.dto.kafka.serializer.DateSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class OrderEvent(
    val memberId: Long,
    val couponId: Long,
//    val statusCode: String = StatusConstant.ORDER_STATUS_CODE_RECEIPT,
    @Serializable(DateSerializer::class)
    val orderDate: Date,
    val phone: String,
    val address: String
)
