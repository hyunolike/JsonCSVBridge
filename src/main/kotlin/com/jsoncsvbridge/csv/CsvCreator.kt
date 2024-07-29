package com.jsoncsvbridge.csv

interface CsvCreator {
    fun createCsv(data: String, outputPath: String)
}