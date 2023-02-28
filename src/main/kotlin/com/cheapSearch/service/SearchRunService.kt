package com.cheapSearch.service

import com.cheapSearch.model.SearchRequest
import com.cheapSearch.model.SearchResponse

interface SearchRunService {
    suspend fun searchRun(searchRequest: SearchRequest): SearchResponse
}
