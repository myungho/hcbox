package com.hcbox.api.dto

import kotlinx.serialization.Serializable

class OrderDetailDto {
    class OrderDetailReadDto{
        var id: Long? = null
        var productId: Long? = null
        var quantity: Long? = null
    }

    @Serializable
    class OrderDetailUpsertDto{
        var productId: Long? = null
        var size: Long? = null
        var quantity: Long? = null
    }
}
