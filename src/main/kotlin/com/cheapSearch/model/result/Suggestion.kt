package com.cheapSearch.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class Suggestion(
    val name: String,
    val params: Params,
    @JsonProperty("short_description")
    val shortDescription: String
)