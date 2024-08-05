package com.jsoncsvbridge.json

import com.jsoncsvbridge.csv.CsvCreator
import com.jsoncsvbridge.csv.MergeCsvCreator

class MergeJsonToCsvCreator(
    private val records: MergeJsonRecords,
    private val validate: JsonDataValidator,
    private val writer: JsonToCsvDataWriter
) : MergeCsvCreator {
    override fun createCsv(data: String, outputPath: String) {
        throw UnsupportedOperationException("Use createMergedCsv for merging JSON data")
    }

    override fun createMergedCsv(data1: String, data2: String, outputPath: String)  {
        try {
            val allRecords = records.combine(listOf(data1, data2))

            writer.write(validate.validate(allRecords.toString()), outputPath)
        } catch (e: Exception) {
            throw e
        }
    }
}