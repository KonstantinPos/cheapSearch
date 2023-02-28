package com.cheapSearch.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Запрос поиск дешевого тура")
data class SearchRequest(

    @Schema(
        description = "Страна",
        example = "Thailand"
    )
    val country: String
)