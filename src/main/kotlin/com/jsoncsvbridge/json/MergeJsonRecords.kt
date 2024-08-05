package com.jsoncsvbridge.json

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jsoncsvbridge.csv.CsvRecodes

class MergeJsonRecords : CsvRecodes {
    override fun combine(data: List<String>): String {
        val mapper = jacksonObjectMapper()
        val allRecords = mutableListOf<Map<String, Any?>>()

        // Parse and merge all JSON strings
        for (jsonString in data) {
            val records: List<Map<String, Any?>> = if (jsonString.trim().startsWith("[")) {
                // Parse as a list of maps
                mapper.readValue(jsonString)
            } else {
                // Parse as a single map
                listOf(mapper.readValue(jsonString))
            }
            allRecords.addAll(records)
        }

        return mapper.writeValueAsString(allRecords)
    }
}