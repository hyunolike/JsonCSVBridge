package com.jsoncsvbridge.csv

import java.io.File

class FileCsvWriter : CsvWriter {
    override fun write(data: String, outputPath: String) {
        File(outputPath).writeText(data)
    }
}