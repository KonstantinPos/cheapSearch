package com.cheapSearch

import com.cheapSearch.configuration.properties.BotProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(BotProperties::class)
@SpringBootApplication
class CheapSearchApplication

fun main(args: Array<String>) {
    runApplication<CheapSearchApplication>(*args)
}
