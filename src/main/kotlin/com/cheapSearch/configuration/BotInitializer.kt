package com.cheapSearch.configuration

import com.cheapSearch.service.TelegramPollingBot
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession


@Configuration
class BotInitializer(
    private val telegramPollingBot: TelegramPollingBot
) {
    @Bean
    fun telegramBotsApi(): TelegramBotsApi? {
        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotsApi.registerBot(telegramPollingBot)
        return telegramBotsApi
    }
}