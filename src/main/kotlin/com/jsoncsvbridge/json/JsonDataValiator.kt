package com.jsoncsvbridge.json

import com.jsoncsvbridge.csv.CsvValidator
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class JsonDataValidator : CsvValidator {
    override fun validate(jsonString: String): List<Map<String, Any?>> {
        try {
            val jsonArray = JSONArray(jsonString)
            return (0 until jsonArray.length()).map { validateAndClean(jsonArray.getJSONObject(it)) }
        } catch (e: JSONException) {
            throw IllegalArgumentException("Invalid JSON format: ${e.message}", e)
        }
    }

    private fun validateAndClean(jsonObject: JSONObject): Map<String, Any?> {
        return jsonObject.keys().asSequence().associate { key ->
            key to when {
                jsonObject.isNull(key) -> null
                jsonObject.get(key) is JSONObject -> validateAndClean(jsonObject.getJSONObject(key))
                jsonObject.get(key) is JSONArray -> validateJsonArray(jsonObject.getJSONArray(key))
                else -> jsonObject.get(key)
            }
        }
    }

    private fun validateJsonArray(jsonArray: JSONArray): List<Any?> {
        return (0 until jsonArray.length()).map { index ->
            when {
                jsonArray.isNull(index) -> null
                jsonArray.get(index) is JSONObject -> validateAndClean(jsonArray.getJSONObject(index))
                jsonArray.get(index) is JSONArray -> validateJsonArray(jsonArray.getJSONArray(index))
                else -> jsonArray.get(index)
            }
        }
    }
}