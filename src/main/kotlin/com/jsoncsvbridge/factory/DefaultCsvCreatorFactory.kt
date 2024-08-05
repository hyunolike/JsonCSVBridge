package com.jsoncsvbridge.factory

import com.jsoncsvbridge.csv.CsvCreator
import com.jsoncsvbridge.csv.DefaultCsvFormatter
import com.jsoncsvbridge.csv.MergeCsvCreator
import com.jsoncsvbridge.json.*
import org.springframework.stereotype.Component

@Component
class DefaultCsvCreatorFactory : CsvCreatorFactory {
    override fun createCsvCreator(type: String): CsvCreator {
        return when (type) {
            "json" -> JsonToCsvCreator(
                DefaultCsvFormatter(),
                JsonDataFilter(),
                JsonDataValidator(),
                JsonToCsvDataWriter()
            )
            "mergeJson" -> MergeJsonToCsvCreator(
                MergeJsonRecords(),
                JsonDataValidator(),
                JsonToCsvDataWriter()
            )
            else -> throw IllegalArgumentException("Unknown type")
        }
    }

    // generateCsv 24.08.03
    companion object {
        @JvmStatic
        fun generateCsv(type : String): CsvCreator {
            return DefaultCsvCreatorFactory().createCsvCreator(type)
        }

        @JvmStatic
        fun generateMergeCsv(): MergeCsvCreator {
            return DefaultCsvCreatorFactory().createCsvCreator("mergeJson") as MergeCsvCreator
        }
    }
}