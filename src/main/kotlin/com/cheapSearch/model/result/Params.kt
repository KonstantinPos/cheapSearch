package com.cheapSearch.model.result

data class Params(
    val adults: Int,
    val cache_operators_by_status: CacheOperatorsByStatus,
    val cache_strategy: String,
    val cache_time: Int,
    val depart_city_id: Int,
    val destination: Destination,
    val duration_from: Int,
    val duration_to: Int,
    val kids: Int,
    val kids_ages: List<Int>,
    val start_from: String,
    val start_to: String,
    val tag: String,
    val ticket_strategy: String
)