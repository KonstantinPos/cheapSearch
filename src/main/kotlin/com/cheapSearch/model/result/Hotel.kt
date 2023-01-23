package com.cheapSearch.model.result

data class Hotel(
    val beachLine: Int,
    val beachPropertyId: Int,
    val beachTypeIds: List<Int>,
    val coords: Coords,
    val description: String,
    val id: Int,
    val is_recommended: Boolean,
    val name: String,
    val openingDate: String? = null,
    val rating: Double,
    val reviewsCount: Int,
    val stars: Int,
    val tags: List<Any>
)