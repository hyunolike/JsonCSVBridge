package com.jsoncsvbridge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JsonCsvBridgeApplication

fun main(args: Array<String>) {
	runApplication<JsonCsvBridgeApplication>(*args)
}
