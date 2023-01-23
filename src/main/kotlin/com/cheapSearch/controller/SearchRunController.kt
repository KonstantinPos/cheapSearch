package com.cheapSearch.controller

import com.cheapSearch.model.search.SearchRunRequest
import com.cheapSearch.model.search.SearchRunResponse
import com.cheapSearch.service.SearchRunService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "search-run")
@RestController
class SearchRunController(
    private val searchRunService: SearchRunService
) {
    @Operation(description = "Запуск поиска дешевого тура")
    @PostMapping("/run")
    suspend fun searchRun(@RequestBody searchRunRequest: SearchRunRequest): SearchRunResponse {
        return coroutineScope {
            searchRunService.searchRun(searchRunRequest)
        }
    }
}
