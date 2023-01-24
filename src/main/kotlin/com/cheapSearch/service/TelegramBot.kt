package com.cheapSearch.service

import com.cheapSearch.configuration.properties.BotProperties
import com.cheapSearch.model.result.ResultResponse
import com.cheapSearch.model.search.Search
import com.cheapSearch.model.search.SearchRunRequest
import com.cheapSearch.model.search.SearchRunResponse
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
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
                "/listgames" -> startCommandReceived2(chatId, update.message.chat.firstName)
                else -> sendMessage(chatId, "Sorry, command was not recognized")
            }
        }
    }

    private fun startCommandReceived(chatId: Long, firstName: String) {
//        val answer = "Hi, $firstName, nice to meet you"
        val answer = "/mygames - edit your games (https://core.telegram.org/bots/games) [beta]\n" +
                "/newgame - create a new game (https://core.telegram.org/bots/games)\n" +
                "/listgames - get a list of your games\n" +
                "/editgame - edit a game\n" +
                "/deletegame - delete an existing game"
        sendMessage(chatId, answer)
    }

    private fun startCommandReceived2(chatId: Long, firstName: String) {

        val searchId = runBlocking {
            webClient.post()
                .uri("https://www.onlinetours.ru/api/v1/searches")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(
                    SearchRunRequest(
                        search = Search(
                            sourceId = 20001,
                            destinationId = 70,
                            destinationType = "Wizard::Suggestion",
                            operatorIds = emptyList(),
                            regionIds = null,
                            startFrom = "2023-03-18",
                            startTo = "2023-03-18",
                            durationFrom = 8,
                            durationTo = 8,
                            adults = 2,
                            kids = 1,
                            kidsAges = listOf(9),
                            experiment = "multiresort_search"
                        )
                    )
                )
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