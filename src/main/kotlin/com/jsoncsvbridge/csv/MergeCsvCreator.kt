package com.jsoncsvbridge.csv

interface MergeCsvCreator : CsvCreator {
    fun createMergedCsv(data1: String, data2: String, outputPath: String)
}