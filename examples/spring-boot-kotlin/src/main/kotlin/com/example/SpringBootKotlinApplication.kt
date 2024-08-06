package com.example

import com.jsoncsvbridge.factory.DefaultCsvCreatorFactory.Companion.createCsv
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
class CsvCreatorRunner : CommandLineRunner {
    override fun run(vararg args: String) {
        val outputDir = File("csv_output").apply { mkdirs() }

        // JSON to CSV
        // TODO: 라이브러리 호출 지점 수정 generateCsv 24.08.03
        val jsonCreator = createCsv("json")
        val jsonOutputPath = outputDir.resolve("output_json.csv").absolutePath

        // JSON to CSV
        val jsonInput = """
            [
                {"name": "Hyunho", "age": 30, "city": "New York"},
                {"name": "Bob", "age": 25, "city": "Los Angeles"},
                {"name": "Charlie", "age": 35, "city": "Chicago"}
            ]
        """

        jsonCreator.createCsv(jsonInput, jsonOutputPath)
        println("JSON to CSV conversion completed. File saved at: $jsonOutputPath")
    }
}
