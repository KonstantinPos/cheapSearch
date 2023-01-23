package com.cheapSearch.model.result

data class CheapestOfferAlternate(
    val checkInDate: String,
    val checkOutDate: String,
    val duration: Int,
    val earlyBooking: Boolean? = null,
    val endDate: String,
    val id: String,
    val mealType: Int,
    val mealTypeName: String,
    val oilTax: Int,
    val `operator`: Int,
    val operatorName: String,
    val originalPrice: Int,
    val price: Int,
    val priceBeforeDiscount: Int,
    val regularFlight: Boolean,
    val roomType: String? = null,
    val roomTypeName: String,
    val startDate: String,
    val transport: String
)