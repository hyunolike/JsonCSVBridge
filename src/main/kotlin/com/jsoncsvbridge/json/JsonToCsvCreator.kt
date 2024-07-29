package com.jsoncsvbridge.json

import com.jsoncsvbridge.csv.CsvCreator
import com.jsoncsvbridge.csv.CsvFormatter
import com.jsoncsvbridge.csv.CsvWriter
import com.jsoncsvbridge.csv.FormattingOptions
import com.jsoncsvbridge.filter.DataFilter
import com.jsoncsvbridge.filter.FilterCriteria

class JsonToCsvCreator(
    private val formatter: CsvFormatter,
    private val filter: DataFilter,
    private val writer: CsvWriter
) : CsvCreator {
    override fun createCsv(data: String, outputPath: String) {
        try {
            // val filteredData = filter.filter(data, FilterCriteria())
            // val formattedData = formatter.format(filteredData, FormattingOptions())
            writer.write(data, outputPath)
        } catch (e: Exception) {
            throw e
        }
    }
}