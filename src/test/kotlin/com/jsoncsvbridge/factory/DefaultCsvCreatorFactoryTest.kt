@file:Suppress("ktlint:standard:no-wildcard-imports")

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.jsoncsvbridge.csv.MergeCsvCreator
import com.jsoncsvbridge.factory.DefaultCsvCreatorFactory
import com.jsoncsvbridge.json.JsonToCsvCreator
import com.jsoncsvbridge.json.MergeJsonToCsvCreator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

class DefaultCsvCreatorFactoryTest {
    private val factory = DefaultCsvCreatorFactory()
    private val objectMapper = jacksonObjectMapper()

    @Test
    fun `test createCsvCreator with json type`() {
        val creator = factory.createCsvCreator("json")
        assertNotNull(creator)
        assertTrue(creator is JsonToCsvCreator)
    }

    @Test
    fun `test createCsvCreator with mergeJson type`() {
        val creator = factory.createCsvCreator("mergeJson")
        assertNotNull(creator)
        assertTrue(creator is MergeJsonToCsvCreator)
    }

    @Test
    fun `test createCsvCreator with unknown type`() {
        assertThrows<IllegalArgumentException> {
            factory.createCsvCreator("unknown")
        }
    }

    @Test
    fun `test generateCsv with json type`() {
        val creator = DefaultCsvCreatorFactory.generateCsv("json")
        assertNotNull(creator)
        assertTrue(creator is JsonToCsvCreator)
    }

    @Test
    fun `test generateMergeCsv`() {
        val creator = DefaultCsvCreatorFactory.generateMergeCsv()
        assertNotNull(creator)
        assertTrue(creator is MergeJsonToCsvCreator)
    }

    @Test
    fun `test createCsv and validate contents`(
        @TempDir tempDir: Path,
    ) {
        val creator = factory.createCsvCreator("json")
        val outputPath = tempDir.resolve("test_output.csv").toString()
        val data = """
            [
                {"name": "John", "age": 30, "city": "New York"},
                {"name": "Alice", "age": 25, "city": "London"},
                {"name": "Bob", "age": 35, "city": "Paris"}
            ]
        """

        creator.createCsv(data, outputPath)

        val file = File(outputPath)
        assertTrue(file.exists(), "CSV file should be created")
        val lines = file.readLines()
        assertEquals(4, lines.size)

        val headers = lines[0].split(",")
        assertTrue(headers.contains("name"))
        assertTrue(headers.contains("age"))
        assertTrue(headers.contains("city"))

        assertEquals("New York,John,30", lines[1])
        assertEquals("London,Alice,25", lines[2])
        assertEquals("Paris,Bob,35", lines[3])
    }

    @Test
    fun `test createMergedCsv and validate contents`(
        @TempDir tempDir: Path,
    ) {
        val creator = factory.createCsvCreator("mergeJson") as MergeCsvCreator
        val outputPath = tempDir.resolve("test_merged_output.csv").toString()
        val data1 = """
            [
                {"name": "John", "age": 30, "city": "New York"}
            ]
        """
        val data2 = """
            [
                {"name": "Alice", "age": 25, "city": "London"},
                {"name": "Bob", "age": 35, "city": "Paris"}
            ]
        """

        creator.createMergedCsv(data1, data2, outputPath)

        val file = File(outputPath)
        assertTrue(file.exists(), "Merged CSV file should be created")
        val lines = file.readLines()
        assertEquals(4, lines.size)
        assertEquals("city,name,age", lines[0])
        assertEquals("New York,John,30", lines[1])
        assertEquals("London,Alice,25", lines[2])
        assertEquals("Paris,Bob,35", lines[3])
    }

    @Test
    fun `test createMergedCsv with different key-value pairs and validate contents`(
        @TempDir tempDir: Path,
    ) {
        val creator = factory.createCsvCreator("mergeJson") as MergeCsvCreator
        val outputPath = tempDir.resolve("test_merged_output.csv").toString()
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

        creator.createMergedCsv(data1, data2, outputPath)

        val file = File(outputPath)
        assertTrue(file.exists(), "Merged CSV file should be created")
        val lines = file.readLines()
        assertEquals(4, lines.size)

        val headers = lines[0].split(",")
        assertTrue(headers.contains("name"))
        assertTrue(headers.contains("age"))
        assertTrue(headers.contains("city"))
        assertTrue(headers.contains("country"))
        assertTrue(headers.contains("occupation"))
        assertTrue(headers.contains("hobby"))

        assertEquals("USA,New York,John,30,NULL,NULL", lines[1])
        assertEquals("NULL,London,Alice,25,Engineer,NULL", lines[2])
        assertEquals("NULL,Paris,Bob,35,NULL,Photography", lines[3])
    }
}
