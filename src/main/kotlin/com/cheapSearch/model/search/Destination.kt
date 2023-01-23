package com.cheapSearch.model.search

import com.fasterxml.jackson.annotation.JsonProperty

data class Destination(
    val id: Int,
    val type: String,
    val name: String,
    @JsonProperty("name_to")
    val nameTo: String
)
