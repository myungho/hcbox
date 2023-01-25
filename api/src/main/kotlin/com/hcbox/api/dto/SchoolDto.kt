package com.hcbox.api.dto

class SchoolDto {
    data class SchoolReadDto(
        var id: Long,
        var name: String? = null,
        var staffName: String? = null,
        var phone: String? = null,
    )

    data class SchoolUpsertDto(
        var name: String? = null,
        var staffName: String? = null,
        var phone: String? = null,
    )
}
