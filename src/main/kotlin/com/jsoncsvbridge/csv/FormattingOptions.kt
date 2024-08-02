package com.jsoncsvbridge.csv

data class FormattingOptions(
    val delimiter: Char = ',',
    val encoding: String = "UTF-8",
    val lineTerminator: String = "\n"
)