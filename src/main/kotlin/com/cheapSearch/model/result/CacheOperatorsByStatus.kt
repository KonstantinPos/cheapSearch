package com.cheapSearch.model.result

data class CacheOperatorsByStatus(
    val empty: List<Int>,
    val fresh: List<Int>,
    val mapping_empty: List<Int>
)