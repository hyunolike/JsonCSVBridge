package com.example

import com.jsoncsvbridge.factory.DefaultCsvCreatorFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.io.File

@SpringBootApplication
class SpringBootKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinApplication>(*args)
}

@Component
class CsvCreatorRunner() : CommandLineRunner {
    override fun run(vararg args: String) {
        val outputDir = File("csv_output").apply { mkdirs() }

        // JSON to CSV
        val jsonCreator = DefaultCsvCreatorFactory().createCsvCreator("json");
        val jsonOutputPath = outputDir.resolve("output_json.csv").absolutePath

        // JSON to CSV
        val jsonInput = """
            [
                {"name": "Alice", "age": 30, "city": "New York"},
                {"name": "Bob", "age": 25, "city": "Los Angeles"},
                {"name": "Charlie", "age": 35, "city": "Chicago"}
            ]
        """

        jsonCreator.createCsv(jsonInput, jsonOutputPath)
        println("JSON to CSV conversion completed. File saved at: $jsonOutputPath")
    }
}