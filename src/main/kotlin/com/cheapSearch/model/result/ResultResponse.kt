package com.cheapSearch.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class ResultResponse(
    val favoriteHotels: FavoriteHotels,
    val hasInstantlyBookableOffers: Boolean? = null,
    val results: List<Result>,
    val stats: Stats,
    @JsonProperty("suggested_subregions")
    val suggestedSubregions: SuggestedSubregions
)