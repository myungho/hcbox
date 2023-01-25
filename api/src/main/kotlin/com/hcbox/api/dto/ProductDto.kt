package com.hcbox.api.dto

class ProductDto {
    data class ProductUpsertDto(
        var seasonType: Integer? = null,
        var name: String? = null,
        var typeCode: String? = null,
        var price: Long? = null,
    )
    data class ProductReadDto(
        var id: Long,
        var seasonType: Integer? = null,
        var name: String? = null,
        var typeCode: String? = null,
        var price: Long? = null,
    )
}
