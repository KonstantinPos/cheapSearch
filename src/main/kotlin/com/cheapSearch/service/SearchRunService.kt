package com.cheapSearch.service

import com.cheapSearch.model.SearchRunRequest
import com.cheapSearch.model.SearchRunResponse

interface SearchRunService {
    suspend fun searchRun(searchRunRequest: SearchRunRequest): SearchRunResponse
}
