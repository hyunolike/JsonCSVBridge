package com.jsoncsvbridge.csv

interface CsvWriter {
    fun write(data: List<Map<String, Any?>>, outputPath: String)
}
