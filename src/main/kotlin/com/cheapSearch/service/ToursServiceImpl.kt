package com.cheapSearch.service

import com.cheapSearch.model.result.ResultResponse
import com.cheapSearch.model.search.SearchRunResponse
import com.cheapSearch.utils.createSearchRunRequest
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class ToursServiceImpl(
    private val webClient: WebClient
) : ToursService {

    override suspend fun getMinCostTour(destinationId: Int): String {
        val searchId =
            webClient.post()
                .uri("https://www.onlinetours.ru/api/v1/searches")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(createSearchRunRequest(destinationId))
                .exchangeToMono { response ->
                    response.bodyToMono(SearchRunResponse::class.java)
                }.awaitFirst().id

        val resultResult =
            webClient.get()
                .uri("https://www.onlinetours.ru/api/v1/searches/$searchId/results?sort=cheap&ticket_strategy=include&page=1&per_page=100")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeToMono { response ->
                    response.bodyToMono(ResultResponse::class.java)
                }.awaitFirst()


        val minCost = resultResult!!.results.minOf { it.cheapestOffer.price }
        return minCost.toString()
    }
}