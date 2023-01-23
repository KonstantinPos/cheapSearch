package com.cheapSearch.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class Params(
    val adults: Int,
    @JsonProperty("cache_operators_by_status")
    val cacheOperatorsByStatus: CacheOperatorsByStatus,
    @JsonProperty("cache_strategy")
    val cacheStrategy: String,
    @JsonProperty("cache_time")
    val cacheTime: Int,
    @JsonProperty("depart_city_id")
    val departCityId: Int,
    val destination: Destination,
    @JsonProperty("duration_from")
    val durationFrom: Int,
    @JsonProperty("duration_to")
    val durationTo: Int,
    val kids: Int,
    @JsonProperty("kids_ages")
    val kidsAges: List<Int>,
    @JsonProperty("start_from")
    val startFrom: String,
    @JsonProperty("start_to")
    val startTo: String,
    val tag: String,
    @JsonProperty("ticket_strategy")
    val ticketStrategy: String
)