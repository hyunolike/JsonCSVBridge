package com.jsoncsvbridge.json

import com.jsoncsvbridge.csv.CsvWriter
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class JsonToCsvDataWriter : CsvWriter {
    override fun write(data: List<Map<String, Any?>>, outputPath: String) {
        if (data.isEmpty()) {
            println("데이터가 비어있습니다. CSV 파일이 생성되지 않았습니다.")
            return
        }

        File(outputPath).printWriter().use { out ->
            // 헤더 쓰기
            val headers = data.flatMap { it.keys }.toSet()
            out.println(headers.joinToString(","))

            // 데이터 쓰기
            data.forEach { row ->
                val rowValues = headers.map { header ->
                    escapeCsvValue(row[header]?.toString() ?: "NULL")
                }
                out.println(rowValues.joinToString(","))
            }
        }
        println("CSV 파일이 생성되었습니다: $outputPath")
    }

    private fun escapeCsvValue(value: String): String {
        return if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            "\"${value.replace("\"", "\"\"")}\""
        } else {
            value
        }
    }
}