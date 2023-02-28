package com.cheapSearch.service

import com.cheapSearch.configuration.properties.BotProperties
import com.cheapSearch.model.SearchRequest
import com.cheapSearch.model.SearchResponse
import com.cheapSearch.model.result.ResultResponse
import com.cheapSearch.model.search.SearchRunResponse
import com.cheapSearch.utils.ONLINE_TOURS_URL
import com.cheapSearch.utils.countries
import com.cheapSearch.utils.createSearchRunRequest
import kotlinx.coroutines.reactive.awaitFirst
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class SearchRunServiceImpl(
    private val webClient: WebClient,
    private val telegramBot: TelegramBot,
    private val botProperties: BotProperties
) : SearchRunService {
    private val logger = LoggerFactory.getLogger(javaClass)
    override suspend fun searchRun(searchRequest: SearchRequest): SearchResponse {
        logger.debug("Start search min cost for ${searchRequest.country}")
        val searchId = webClient.post()
            .uri(ONLINE_TOURS_URL)
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .bodyValue(
                createSearchRunRequest(countries.filterKeys { it == searchRequest.country }.values.first())
            )
            .exchangeToMono { response ->
                response.bodyToMono(SearchRunResponse::class.java)
            }.awaitFirst().id

        val minTourCost = webClient.get()
            .uri("$ONLINE_TOURS_URL/$searchId/results?sort=cheap&ticket_strategy=include&page=1&per_page=100")
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .exchangeToMono { response ->
                response.bodyToMono(ResultResponse::class.java)
            }.awaitFirst().results.minOf { it.cheapestOffer.price }
        telegramBot.sendMessage(
            botProperties.chatId,
            "Min cost for ${searchRequest.country}: $minTourCost"
        )
        logger.debug("Min cost for ${searchRequest.country}: $minTourCost")
        return SearchResponse(
            country = searchRequest.country,
            cost = minTourCost
        )
    }
}
