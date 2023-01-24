package com.cheapSearch.service

import com.cheapSearch.configuration.properties.BotProperties
import com.cheapSearch.model.result.ResultResponse
import com.cheapSearch.model.search.SearchRunRequest
import com.cheapSearch.model.search.SearchRunResponse
import com.cheapSearch.utils.createSearchRunRequest
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class TelegramBot(
    private val botProperties: BotProperties,
    private val webClient: WebClient
) : TelegramLongPollingBot() {
    override fun getBotToken(): String {
        return botProperties.token
    }

    override fun getBotUsername(): String {
        return botProperties.name
    }

    override fun onUpdateReceived(update: Update?) {
        if (update != null && update.message.hasText()) {
            val messageText = update.message.text
            val chatId = update.message.chatId
            when (messageText) {
                "/start" -> startCommandReceived(chatId, update.message.chat.firstName)
                "/Thailand" -> startCommandReceived2(chatId, update.message.chat.firstName, createSearchRunRequest(71))
                "/Sri Lanka" -> startCommandReceived2(chatId, update.message.chat.firstName, createSearchRunRequest(70))
                else -> sendMessage(chatId, "Sorry, command was not recognized")
            }
        }
    }

    private fun startCommandReceived(chatId: Long, firstName: String) {
        val answer = "Hi, $firstName, nice to meet you"
        sendMessage(chatId, answer)
    }

    private fun startCommandReceived2(chatId: Long, firstName: String, createSearchRunRequest: SearchRunRequest) {

        val searchId = runBlocking {
            webClient.post()
                .uri("https://www.onlinetours.ru/api/v1/searches")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(createSearchRunRequest)
                .exchangeToMono { response ->
                    response.bodyToMono(SearchRunResponse::class.java)
                }.awaitFirst().id
        }
        val resultResult = runBlocking {
            webClient.get()
                .uri("https://www.onlinetours.ru/api/v1/searches/$searchId/results?sort=cheap&ticket_strategy=include&page=1&per_page=100")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeToMono { response ->
                    response.bodyToMono(ResultResponse::class.java)
                }.awaitFirst()
        }

        val minCost2 = resultResult!!.results.minOf { it.cheapestOffer.price }
        sendMessage(chatId, minCost2.toString())
    }

    fun sendMessage(chatId: Long, textToSend: String) {
        val send = SendMessage(chatId.toString(), textToSend)
        execute(send)
    }
}