package com.cheapSearch.service

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
            toursService.getMinCostTour(destination.first.first)
        }
        telegramBot.sendMessage(CHAT_ID, "Min cost for ${destination.first.second} = $minCost")
    }

    companion object {
        const val CHAT_ID = 442595576L
        val destination = Pair(71 to "Thailand", 70 to "Sri Lanka")
    }
}