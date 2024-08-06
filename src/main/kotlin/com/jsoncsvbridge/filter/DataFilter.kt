package com.jsoncsvbridge.filter

interface DataFilter {
    fun filter(data: String, criteria: FilterCriteria): String
}
