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
    ) {
        fun map(productEntity: ProductEntity) {
            productEntity.name = this.name
            productEntity.price = this.price
            productEntity.discount = this.discount
            productEntity.stock = this.stock
            productEntity.imagePath1 = this.imagePath1
            productEntity.imagePath2 = this.imagePath2
            productEntity.imagePath3 = this.imagePath3
            productEntity.detail = this.detail
            productEntity.code = this.code
            productEntity.brandCode = this.brandCode
        }
    }

    data class ProductReadDto(
        var id: Long,
        var name: String,
        var alias: String?,
        var category: String,
    )
}
