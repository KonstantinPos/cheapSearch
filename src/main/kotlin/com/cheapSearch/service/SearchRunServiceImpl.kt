package com.cheapSearch.service

import com.cheapSearch.model.SearchRunRequest
import com.cheapSearch.model.SearchRunResponse
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class SearchRunServiceImpl : SearchRunService {
    private val webClient = WebClient.builder().build()
    override suspend fun searchRun(searchRunRequest: SearchRunRequest): SearchRunResponse {
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
