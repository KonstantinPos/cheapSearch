package com.cheapSearch.model.result

data class SuggestedSubregions(
    val country: String,
    val suggestions: List<Suggestion>
)