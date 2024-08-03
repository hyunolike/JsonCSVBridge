package com.jsoncsvbridge.json

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class JsonDataValidatorTest {
    private val validator = JsonDataValidator()

    @Test
    fun `test validate with various data types`() {
        val jsonInput = """
            [
                {"id": 1, "name": "John Doe", "email": "john@example.com", "age": 30},
                {"id": 2, "name": "Jane Smith", "email": null, "city": "New York"},
                {"id": 3, "name": "Bob Johnson", "age": null, "isStudent": true},
                {"id": 4, "name": "Alice Brown", "score": 95, "subjects": ["Math", "Science"]}
            ]
        """.trimIndent()

        val result = validator.validate(jsonInput)

        assertEquals(4, result.size)
        assertEquals(1, result[0]["id"])
        assertEquals("John Doe", result[0]["name"])
        assertEquals("john@example.com", result[0]["email"])
        assertEquals(30, result[0]["age"])

        assertNull(result[1]["email"])
        assertEquals("New York", result[1]["city"])

        assertTrue(result[2]["isStudent"] as Boolean)
        assertNull(result[2]["age"])

        assertEquals(95, result[3]["score"])
        assertTrue(result[3]["subjects"].toString().contains("Math"))
    }

    @Test
    fun `test validate with invalid JSON`() {
        val jsonInput = "{ \"This is not valid JSON\" }"
        assertThrows(IllegalArgumentException::class.java) {
            validator.validate(jsonInput)
        }
    }
}