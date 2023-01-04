package com.hcbox.api.dto

class ProductDto {
    data class ProductUpsertDto(
        var name: String,
        var price: Long,
        var discount: Long,
        var stock: Long,
        var imagePath1: String?,
        var imagePath2: String?,
        var imagePath3: String?,
        var detail: String?,
        var code: String,
        var brandCode: String
    )
    data class ProductReadDto(
        var id: Long,
        var name: String,
        var price: Long,
        var discount: Long,
        var stock: Long,
        var imagePath1: String?,
        var imagePath2: String?,
        var imagePath3: String?,
        var detail: String?,
        var code: String,
        var brandCode: String
    )
}
