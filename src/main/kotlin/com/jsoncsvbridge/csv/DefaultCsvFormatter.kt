package com.jsoncsvbridge.csv

class DefaultCsvFormatter : CsvFormatter {
    override fun format(data: String, options: FormattingOptions): String {
        return data.replace(",", options.delimiter.toString())
            .replace("\n", options.lineTerminator)
    }
}