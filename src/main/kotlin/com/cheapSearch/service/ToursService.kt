package com.cheapSearch.service

interface ToursService {
    suspend fun getMinCostTour(destinationId: Int):String
}