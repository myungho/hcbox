package com.hcbox.api.dto

class ProductDto {
    class ProductUpsertDto{
        var seasonType: Integer? = null
        var name: String? = null
        var typeCode: String? = null
        var price: Long? = null
    }
    class ProductReadDto{
        var id: Long? = null
        var seasonType: Integer? = null
        var name: String? = null
        var typeCode: String? = null
        var price: Long? = null
    }
}
