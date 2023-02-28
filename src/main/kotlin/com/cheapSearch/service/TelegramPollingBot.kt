package com.cheapSearch.service

import com.cheapSearch.configuration.properties.BotProperties
import com.cheapSearch.model.SearchRequest
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class TelegramPollingBot(
    private val botProperties: BotProperties,
    private val searchRequestComponent: SearchRequestComponent
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
                "Thailand" -> startCommandReceived2(chatId, messageText)
                "Sri Lanka" -> startCommandReceived2(chatId, messageText)
                else -> sendMessage(chatId.toString(), "Sorry, command was not recognized")
            }
        }
    }

    private fun startCommandReceived(chatId: Long, firstName: String) {
        val answer = "Hi, $firstName, nice to meet you"
        sendMessage(chatId.toString(), answer)
    }

    private fun startCommandReceived2(chatId: Long, messageText: String) {
        val searchResponse = runBlocking {
            searchRequestComponent.searchRun(
                searchRequest = SearchRequest(
                    country = messageText
                )
            )
        }
        sendMessage(chatId.toString(), searchResponse.cost.toString())
    }

    fun sendMessage(chatId: String, textToSend: String) {
        val send = SendMessage(chatId, textToSend)
        execute(send)
    }
}