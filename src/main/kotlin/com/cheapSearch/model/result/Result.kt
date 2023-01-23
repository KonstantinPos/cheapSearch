package com.cheapSearch.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class Result(
    val airConditionerCardInfo: String? = null,
    val airportDistanceCardInfo: String,
    val beachCardInfo: String,
    val cheapestOffer: CheapestOffer,
    val cheapestOfferAlternate: CheapestOfferAlternate? = null,
    val departureDateFrom: String,
    val departureDateTo: String,
    val durationMax: Int,
    val durationMin: Int,
    val freeCancel: Boolean,
    val hotel: Hotel,
    val id: Int,
    val images: List<String>,
    @JsonProperty("instantly_bookable")
    val instantlyBookable: Boolean,
    val internetCardInfo: String? = null,
    val location: String,
    val mealTypes: List<Int>,
    val minCost: Int,
    val offerCount: Int,
    val salesCount: Int,
    val shortLocationCardInfo: String,
    val strongboxCardInfo: String? = null,
    val subTitle: String? = null,
    val title: String,
    val type: String,
    val url: String,
    val weights: Weights
)