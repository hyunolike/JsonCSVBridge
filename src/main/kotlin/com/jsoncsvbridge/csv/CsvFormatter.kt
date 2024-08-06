package com.jsoncsvbridge.csv

interface CsvFormatter {
    fun format(data: String, options: FormattingOptions): String
}
