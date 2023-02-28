package com.cheapSearch.service

import com.cheapSearch.utils.countries
import kotlinx.coroutines.runBlocking
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduleService(
    private val telegramBot: TelegramBot,
    private val toursService: ToursService
) {
    @Scheduled(fixedDelay = 10000)
    fun scheduleFixedDelayTask() {
        val minCost = runBlocking {
            toursService.getMinCostTour(countries.values.first())
        }
        telegramBot.sendMessage(CHAT_ID, "Min cost for ${countries.keys.first()} = $minCost")
    }

    companion object {
        const val CHAT_ID = "442595576"
    }
}