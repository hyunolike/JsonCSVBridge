package com.jsoncsvbridge.factory

import com.jsoncsvbridge.csv.CsvCreator

interface CsvCreatorFactory {
    fun createCsvCreator(type: String): CsvCreator
}
