package com.hcbox.api.dto

class ProductDto {
    class ProductUpsertDto{
        var seasonType: Int? = null
        var gender: Int? = null
        var name: String? = null
        var typeCode: String? = null
        var price: Long? = null
        var schoolId: Long? = null
    }
    class ProductReadDto{
        var id: Long? = null
        var gender: Int? = null
        var seasonType: Int? = null
        var name: String? = null
        var typeCode: String? = null
        var price: Long? = null
        var schoolId: Long? = null
    }
}
