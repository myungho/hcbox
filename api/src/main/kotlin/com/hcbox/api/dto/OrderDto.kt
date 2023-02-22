package com.hcbox.api.dto

import java.util.*

class OrderDto {
    class OrderCreateDto {
        var studentName: String? = null
        var orderDate: Date? = Date()
        var phone: String? = null
        var schoolId: Long? = null
        var orderDetailList: List<OrderDetailDto.OrderDetailUpsertDto>? = null
    }
    class OrderUpdateDto {
        var id: Long? = null
        var studentName: String? = null
        var orderDate: Date? = Date()
        var phone: String? = null
        var schoolId: Long? = null
        var orderDetailList: List<OrderDetailDto.OrderDetailUpsertDto>? = null
    }

    class OrderReadDto {
        var id: Long? = null
        var studentName: String? = null
        var statusCode: Long? = null
        var orderDate: Date? = null
        var phone: String? = null
        var school: SchoolDto.SchoolReadDto? = null
    }

    class OrderReadAllDto {
        var id: Long? = null
        var studentName: String? = null
        var statusCode: Long? = null
        var orderDate: Date? = null
        var phone: String? = null
        var school: SchoolDto.SchoolReadDto? = null
        var orderDetailList: List<OrderDetailDto.OrderDetailReadDto>? = null
    }
}
