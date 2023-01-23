package com.cheapSearch.model.result

import com.fasterxml.jackson.annotation.JsonProperty

data class CacheOperatorsByStatus(
    val empty: List<Int>,
    val fresh: List<Int>,
    @JsonProperty("mapping_empty")
    val mappingEmpty: List<Int>
)