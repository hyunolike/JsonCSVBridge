package com.example

import com.jsoncsvbridge.factory.DefaultCsvCreatorFactory.Companion.generateCsv
import com.jsoncsvbridge.factory.DefaultCsvCreatorFactory.Companion.generateMergeCsv
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
        val jsonOutputPath = outputDir.resolve("output_json.csv").absolutePath
        val mergeJsonOutputPath = outputDir.resolve("output_merge_json.csv").absolutePath

        // JSON to CSV
        // 기능1. JSON 형식 문자열 CSV 파일로 변환
        val jsonCreator = generateCsv("json")

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

        // 기능2. 2개 JSON 형식 문자열 CSV 파일로 변환
        val mergeJsonCreator = generateMergeCsv()

        val data1 = """
            [
                {"name": "John", "age": 30, "city": "New York", "country": "USA"}
            ]
        """
        val data2 = """
            [
                {"name": "Alice", "age": 25, "city": "London", "occupation": "Engineer"},
                {"name": "Bob", "age": 35, "city": "Paris", "hobby": "Photography"}
            ]
        """

        mergeJsonCreator.createMergedCsv(data1, data2, mergeJsonOutputPath)
        println("Merged 2 JSON files and converted to CSV. File saved at: $mergeJsonOutputPath")
    }
}
