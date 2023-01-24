package com.cheapSearch.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class CacheOperatorsByStatus(
    val empty: List<Int>? = null,
    val fresh: List<Int>? = null,
    @JsonProperty("mapping_empty")
    val mappingEmpty: List<Int>? = null
)