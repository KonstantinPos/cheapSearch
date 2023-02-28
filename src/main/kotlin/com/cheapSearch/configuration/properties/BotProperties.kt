package com.cheapSearch.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("bot")
data class BotProperties(
    val name: String,
    val token: String,
    val chatId: String = "442595576"
)
