package com.cheapSearch.model.result

data class Total(
    val filters: Filters,
    val hotelCount: Int,
    val maxCardCost: Int,
    val maxCost: Int,
    val maxHotelArea: Int,
    val minCost: Int,
    val minHotelArea: Int,
    val operators: List<Operator>,
    val pages: Int
)