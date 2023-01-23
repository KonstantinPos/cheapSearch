package com.cheapSearch.service

import com.cheapSearch.model.search.SearchRunRequest
import com.cheapSearch.model.search.SearchRunResponse

interface SearchRunService {
    suspend fun searchRun(searchRunRequest: SearchRunRequest): SearchRunResponse
}
