package com.hcbox.api.dto.kafka

import com.hcbox.api.dto.OrderDetailDto.OrderDetailUpsertDto
import com.hcbox.api.dto.kafka.serializer.DateSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class OrderEvent(
    var eventType: String?,
    var studentName: String,
    @Serializable(DateSerializer::class)
    var orderDate: Date,
    var phone: String,
    var schoolId: Long,
    var orderDetailList: List<OrderDetailUpsertDto>
)
