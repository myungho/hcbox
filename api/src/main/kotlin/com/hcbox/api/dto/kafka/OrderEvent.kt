package com.hcbox.api.dto.kafka

import com.hcbox.api.dto.kafka.serializer.DateSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class OrderEvent(
    val studentName: String,
    @Serializable(DateSerializer::class)
    val orderDate: Date,
    val phone: String,
    val address: String,
    val schoolId: Long
)
