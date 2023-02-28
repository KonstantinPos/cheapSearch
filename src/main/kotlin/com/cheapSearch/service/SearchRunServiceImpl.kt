package com.cheapSearch.service

import com.cheapSearch.configuration.properties.BotProperties
import com.cheapSearch.model.SearchRequest
import com.cheapSearch.model.SearchResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SearchRunServiceImpl(
    private val telegramPollingBot: TelegramPollingBot,
    private val searchRequestComponent: SearchRequestComponent,
    private val botProperties: BotProperties
) : SearchRunService {
    private val logger = LoggerFactory.getLogger(javaClass)
    override suspend fun searchRun(searchRequest: SearchRequest): SearchResponse {
        val searchResponse = searchRequestComponent.searchRun(searchRequest)
        telegramPollingBot.sendMessage(
            botProperties.chatId,
            "Min cost for ${searchRequest.country}: ${searchResponse.cost}"
        )
        logger.debug("Min cost for ${searchRequest.country}: $searchResponse.cost")
        return SearchResponse(
            country = searchRequest.country,
            cost = searchResponse.cost
        )
    }
}
