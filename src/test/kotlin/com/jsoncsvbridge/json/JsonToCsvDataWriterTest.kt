package com.jsoncsvbridge.json

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path


class JsonToCsvDataWriterTest {
    private val writer = JsonToCsvDataWriter()

    @Test
    fun `write should create CSV file with correct content`(@TempDir tempDir: Path) {
        val data = listOf(
            mapOf("name" to "John", "age" to 30, "city" to "New York"),
            mapOf("name" to "Alice", "age" to 25, "city" to "London"),
            mapOf("name" to "Bob", "age" to 35, "city" to "Paris")
        )
        val outputPath = tempDir.resolve("output.csv").toString()

        writer.write(data, outputPath)

        val file = File(outputPath)
        assertTrue(file.exists())
        val lines = file.readLines()
        assertEquals(4, lines.size)
        assertEquals("name,age,city", lines[0])
        assertEquals("John,30,New York", lines[1])
        assertEquals("Alice,25,London", lines[2])
        assertEquals("Bob,35,Paris", lines[3])
    }

    @Test
    fun `write should handle empty data`(@TempDir tempDir: Path) {
        val data = emptyList<Map<String, Any?>>()
        val outputPath = tempDir.resolve("empty.csv").toString()

        writer.write(data, outputPath)

        val file = File(outputPath)
        assertFalse(file.exists())
    }
}