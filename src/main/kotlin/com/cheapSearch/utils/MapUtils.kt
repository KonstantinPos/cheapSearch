package com.cheapSearch.utils

import com.cheapSearch.model.search.Search
import com.cheapSearch.model.search.SearchRunRequest

fun createSearchRunRequest(destinationId: Int): SearchRunRequest {

    return SearchRunRequest(
        search = Search(
            sourceId = 20001,
            destinationId = destinationId,
            destinationType = "Wizard::Suggestion",
            operatorIds = emptyList(),
            regionIds = null,
            startFrom = "2023-03-18",
            startTo = "2023-03-18",
            durationFrom = 8,
            durationTo = 8,
            adults = 2,
            kids = 1,
            kidsAges = listOf(9),
            experiment = "multiresort_search"
        )
    )
}
