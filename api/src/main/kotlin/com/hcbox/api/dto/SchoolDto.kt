package com.hcbox.api.dto

class SchoolDto {
    class SchoolReadDto{
        var id: Long? = null
        var name: String? = null
        var staffName: String? = null
        var phone: String? = null
    }

    class SchoolUpsertDto{
        var name: String? = null
        var staffName: String? = null
        var phone: String? = null
    }
}
