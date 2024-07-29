package com.jsoncsvbridge.json

import com.jsoncsvbridge.csv.CsvWriter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.File

class JsonToCsvDataWriter : CsvWriter {
    override fun write(data: String, outputPath: String) {
        // JSON 배열 파싱
        val jsonArray = try {
            JSONArray(data)
        } catch (e: JSONException) {
            // JSON 데이터가 배열 형식이 아닌 경우, 배열 형식으로 변환
            JSONArray().put(JSONObject(data))
        }

        File(outputPath).printWriter().use { out ->
            // 첫 번째 JSON 객체에서 키를 가져와 CSV 헤더 ��성
            if (jsonArray.length() > 0) {
                val firstObject = jsonArray.getJSONObject(0)
                val headers = firstObject.keys().asSequence().toList()
                out.println(headers.joinToString(","))

                // JSON 배열의 각 객체를 CSV 형식으로 변환
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val row = headers.joinToString(",") { jsonObject.optString(it.toString(), "") }
                    out.println(row)
                }
            }
        }
        println("CSV 파일이 생성되었습니다: $outputPath")
    }
}