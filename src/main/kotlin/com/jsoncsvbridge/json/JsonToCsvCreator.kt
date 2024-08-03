package com.jsoncsvbridge.json

import com.jsoncsvbridge.csv.*
import com.jsoncsvbridge.filter.DataFilter
import com.jsoncsvbridge.filter.FilterCriteria

class JsonToCsvCreator(
    private val formatter: CsvFormatter,
    private val filter: DataFilter,
    private val validate: CsvValidator,
    private val writer: CsvWriter
) : CsvCreator {
    override fun createCsv(data: String, outputPath: String) {
        try {
            // val filteredData = filter.filter(data, FilterCriteria())
            // val formattedData = formatter.format(filteredData, FormattingOptions())

            writer.write(validate.validate(data), outputPath)
        } catch (e: Exception) {
            throw e
        }
    }
}