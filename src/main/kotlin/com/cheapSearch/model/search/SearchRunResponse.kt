package com.cheapSearch.model.search

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SearchRunResponse(

    val params: Params? = null,

    val analytics: Analytics? = null,

    @JsonProperty("intentmedia")
    val intentMedia: IntentMedia? = null,

    @JsonProperty("has_ticket_strategy")
    val hasTicketStrategy: Boolean? = null,

    val id: String? = null,

    val url: String? = null
)
