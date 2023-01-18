package com.cheapSearch.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Params(

    @JsonProperty("depart_city_id")
    val departCityId: Int? = null,
    val destination: Destination? = null,

    @JsonProperty("hotel_ids")
    val hotelIds: String? = null,

    @JsonProperty("region_ids")
    val regionIds: String? = null,

    @JsonProperty("duration_from")
    val durationFrom: Int? = null,

    @JsonProperty("duration_to")
    val durationTo: Int? = null,

    @JsonProperty("start_from")
    val startFrom: String? = null,

    @JsonProperty("start_to")
    val startTo: String? = null,
    val adults: Int? = null,
    val kids: Int? = null,

    @JsonProperty("kids_ages")
    val kidsAges: List<Int?> = emptyList(),
    val operators: List<String?> = emptyList(),

    @JsonProperty("destination_regions")
    val destinationRegions: List<String?> = emptyList()
)
