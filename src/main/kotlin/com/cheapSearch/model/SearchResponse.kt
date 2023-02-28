package com.cheapSearch.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Ответ на запрос поиск дешевого тура")
data class SearchResponse(
    @Schema(
        description = "Страна",
        example = "Thailand"
    )
    val country: String,
    @Schema(description = "Цена",
        example = "244923")
    val cost: Int
)