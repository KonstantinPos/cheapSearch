package com.cheapSearch.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Search(

    @JsonProperty("source_id")
    val sourceId: Int,

    @JsonProperty("destination_id")
    val destinationId: Int,

    @JsonProperty("destination_type")
    val destinationType: String,

    @JsonProperty("operator_ids")
    val operatorIds: List<String> = emptyList(),

    @JsonProperty("region_ids")
    val regionIds: String? = null,

    @JsonProperty("start_from")
    val startFrom: String,

    @JsonProperty("start_to")
    val startTo: String,

    @JsonProperty("duration_from")
    val durationFrom: Int,

    @JsonProperty("duration_to")
    val durationTo: Int,

    val adults: Int,

    val kids: Int,

    @JsonProperty("kids_ages")
    val kidsAges: List<Int>,

    val experiment: String,
)
