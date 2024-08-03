package com.jsoncsvbridge.factory

import com.jsoncsvbridge.csv.CsvCreator
import com.jsoncsvbridge.csv.DefaultCsvFormatter
import com.jsoncsvbridge.csv.FileCsvWriter
import com.jsoncsvbridge.json.JsonDataFilter
import com.jsoncsvbridge.json.JsonToCsvCreator
import com.jsoncsvbridge.json.JsonToCsvDataWriter
import org.springframework.stereotype.Component

@Component
class DefaultCsvCreatorFactory : CsvCreatorFactory {
    override fun createCsvCreator(type: String): CsvCreator {
        return when (type) {
            "json" -> JsonToCsvCreator(
                DefaultCsvFormatter(),
                JsonDataFilter(),
                JsonToCsvDataWriter()
            )
            else -> throw IllegalArgumentException("Unknown type")
        }
    }

    // generateCsv 24.08.03
    companion object {
        @JvmStatic
        fun createCsv(type: String): CsvCreator {
            return DefaultCsvCreatorFactory().createCsvCreator(type)
        }
    }
}