package com.cheapSearch.service

import com.cheapSearch.model.result.ResultResponse
import com.cheapSearch.model.search.SearchRunRequest
import com.cheapSearch.model.search.SearchRunResponse
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class SearchRunServiceImpl : SearchRunService {
    private val webClient = WebClient.builder().build()
    override suspend fun searchRun(searchRunRequest: SearchRunRequest): SearchRunResponse {

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
            .uri("https://www.onlinetours.ru/api/v1/searches/$searchId/results?sort=cheap&ticket_strategy=include&page=1&per_page=14")
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .exchangeToMono { response ->
                response.bodyToMono(ResultResponse::class.java)
            }.awaitFirst()
        println(resultResult.toString())

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
