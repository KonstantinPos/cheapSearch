package com.cheapSearch.model.search

import com.fasterxml.jackson.annotation.JsonProperty

data class Analytics(
    @JsonProperty("country_travel_id")
    val countryTravelId: String? = null,

    @JsonProperty("country_travel_name")
    val countryTravelName: String? = null,

    @JsonProperty("search_term_type")
    val searchTermType: String? = null,

    @JsonProperty("search_term")
    val searchTerm: String? = null
)
