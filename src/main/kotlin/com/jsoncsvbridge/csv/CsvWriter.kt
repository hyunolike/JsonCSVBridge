package com.jsoncsvbridge.csv

interface CsvWriter {
    fun write(data: String, outputPath: String)
}