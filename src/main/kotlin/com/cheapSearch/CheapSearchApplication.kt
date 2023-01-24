package com.cheapSearch

import com.cheapSearch.configuration.properties.BotProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableConfigurationProperties(BotProperties::class)
@EnableScheduling
@SpringBootApplication
class CheapSearchApplication

fun main(args: Array<String>) {
    runApplication<CheapSearchApplication>(*args)
}
