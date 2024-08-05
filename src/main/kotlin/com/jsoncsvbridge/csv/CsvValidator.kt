package com.jsoncsvbridge.csv

interface CsvValidator {
    fun validate(jsonString: String): List<Map<String, Any?>>
}
