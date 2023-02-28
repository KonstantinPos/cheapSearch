package com.cheapSearch.service

import com.cheapSearch.configuration.properties.BotProperties
import com.cheapSearch.model.SearchRequest
import com.cheapSearch.utils.countries
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduleService(
    private val searchRunService: SearchRunService,
    private val telegramPollingBot: TelegramPollingBot,
    private val botProperties: BotProperties
) {
    private val logger = LoggerFactory.getLogger(javaClass)

        @Scheduled(fixedDelay = 10000)
    fun scheduleFixedDelayTask() {
        logger.info("Start getting min cost tour by scheduled")
        val searchResponse = runBlocking {
            searchRunService.searchRun(
                searchRequest = SearchRequest(
                    country = countries.keys.first()
                )
            )
        }
        telegramPollingBot.sendMessage(
            botProperties.chatId,
            "Min cost for ${searchResponse.country}: ${searchResponse.cost}"
        )
        logger.info("Finish getting min cost tour by scheduled")
    }
}