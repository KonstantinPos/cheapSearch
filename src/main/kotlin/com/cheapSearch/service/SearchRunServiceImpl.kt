package com.cheapSearch.service

import com.cheapSearch.model.result.ResultResponse
import com.cheapSearch.model.search.SearchRunRequest
import com.cheapSearch.model.search.SearchRunResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class SearchRunServiceImpl(
    private val webClient: WebClient,
    private val telegramBot: TelegramBot
) : SearchRunService {
    override suspend fun searchRun(searchRunRequest: SearchRunRequest): SearchRunResponse {
        telegramBot.sendMessage(442595576, "Hi from cont")
        while (true) {
            val searchId = webClient.post()
                .uri("https://www.onlinetours.ru/api/v1/searches")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .bodyValue(
                    searchRunRequest
                )
                .exchangeToMono { response ->
                    response.bodyToMono(SearchRunResponse::class.java)
                }.awaitFirst().id

            val resultResult = webClient.get()
                .uri("https://www.onlinetours.ru/api/v1/searches/$searchId/results?sort=cheap&ticket_strategy=include&page=1&per_page=100")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchangeToMono { response ->
                    response.bodyToMono(ResultResponse::class.java)
                }.awaitFirst()

            val minCost2 = resultResult.results.minOf { it.cheapestOffer.price }
            println(minCost2)
            delay(1000L)
        }
        return webClient.post()
            .uri("https://www.onlinetours.ru/api/v1/searches")
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .bodyValue(
                searchRunRequest
            )
            .exchangeToMono { response ->
                response.bodyToMono(SearchRunResponse::class.java)
            }.awaitFirst()
    }
}
