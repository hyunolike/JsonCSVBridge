package com.jsoncsvbridge.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.jsoncsvbridge.filter.DataFilter
import com.jsoncsvbridge.filter.FilterCriteria

class JsonDataFilter : DataFilter {
    private val objectMapper = ObjectMapper()

    override fun filter(data: String, criteria: FilterCriteria): String {
        return when (val jsonNode = objectMapper.readTree(data)) {
            is ObjectNode -> filterObjectNode(jsonNode, criteria)
            is ArrayNode -> filterArrayNode(jsonNode, criteria)
            else -> throw IllegalArgumentException("Unsupported JSON data type")
        }
    }

    private fun filterObjectNode(jsonNode: ObjectNode, criteria: FilterCriteria): String {
        val filteredNode = ObjectNode(objectMapper.nodeFactory).apply {
            jsonNode.fields().asSequence().filter { (key, value) ->
                criteria.conditions.any { it.field == key && it.value == value.asText() }
            }.forEach { (key, value) ->
                this.replace(key, value)
            }
        }
        return objectMapper.writeValueAsString(filteredNode)
    }

    private fun filterArrayNode(jsonNode: ArrayNode, criteria: FilterCriteria): String {
        val filteredArray = ArrayNode(objectMapper.nodeFactory).apply {
            jsonNode.forEach { element ->
                if (element is ObjectNode) {
                    val filteredElement = filterObjectNode(element, criteria)
                    this.add(objectMapper.readTree(filteredElement))
                }
            }
        }
        return objectMapper.writeValueAsString(filteredArray)
    }
}