package com.jsoncsvbridge.filter

data class FilterCriteria(
    val conditions: List<Condition> = emptyList()
)