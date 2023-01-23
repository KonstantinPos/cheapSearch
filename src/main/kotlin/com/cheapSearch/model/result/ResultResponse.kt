package com.cheapSearch.model.result

data class ResultResponse(
    val favoriteHotels: FavoriteHotels,
    val hasInstantlyBookableOffers: Boolean? = null,
    val results: List<Result>,
    val stats: Stats,
    val suggested_subregions: SuggestedSubregions
)