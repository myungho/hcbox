package com.hcbox.common.constant

import java.util.*
import java.util.stream.Collectors

enum class ProductTypeCode(
    val code: String,
    val seasonType: Int,
    val gender: Int,
    val desc: String
) {

    SHIRTS_MALE_SUMMER("10", 0, 0, "셔츠(하계)"),
    SHIRTS_MALE_WINTER("11", 1, 0, "셔츠(동계)"),

    BLOUSE_FEMALE_SUMMER("20", 0, 1, "블라우스(하계)"),
    BLOUSE_FEMALE_WINTER("21", 1, 1, "블라우스(동계)"),

    PANTS_MALE_SUMMER("30", 0, 0, "바지(하계)"),
    PANTS_MALE_WINTER("31", 1, 0, "바지(동계)"),

    SKIRT_FEMALE_SUMMER("40", 0, 1, "치마(하계)"),
    SKIRT_FEMALE_WINTER("41", 1, 1, "치마(동계)"),

    JACKET_MALE("50", 1, 0, "자켓(남자)"),
    JACKET_FEMALE("51", 1, 1, "자켓(여자)");

    fun hasCode(code: String): Boolean {
        return this.code == code
    }

    companion object {
        fun findByCode(code: String): ProductTypeCode? {
            return Arrays.stream(values()).filter { o: ProductTypeCode? -> o!!.hasCode(code) }
                .findAny().orElse(null)
        }

        fun findListByOptions(seasonType: Int, gender: Int): List<ProductTypeCode> {
            return Arrays.stream(values())
                .filter { o: ProductTypeCode? -> o!!.seasonType == seasonType }
                .filter { o: ProductTypeCode? -> o!!.gender == gender }
                .collect(Collectors.toList())
        }
    }
}
