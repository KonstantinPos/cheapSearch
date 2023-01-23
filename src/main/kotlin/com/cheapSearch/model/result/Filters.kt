package com.cheapSearch.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class Filters(
    @JsonProperty("show_free_cancel")
    val showFreeCancel: Boolean,
    @JsonProperty("show_tickets")
    val showTickets: String
)