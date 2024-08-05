package com.example.springbootjava;

import com.jsoncsvbridge.csv.CsvCreator;
import com.jsoncsvbridge.factory.DefaultCsvCreatorFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.File;

@SpringBootApplication
public class SpringBootJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJavaApplication.class, args);
    }
}

@Component
class CsvCreatorRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        File outputDir = new File("csv_output");
        outputDir.mkdirs();

        // JSON to CSV
        CsvCreator jsonCreator = DefaultCsvCreatorFactory.createCsv("json");
        String jsonOutputPath = new File(outputDir, "output_json.csv").getAbsolutePath();

        // JSON to CSV
        String jsonInput = """
            [
                {"name": "Hyunho", "age": 30, "city": "New York"},
                {"name": "Bob", "age": 25, "city": "Los Angeles"},
                {"name": "Charlie", "age": 35, "city": "Chicago"}
            ]
        """;

        jsonCreator.createCsv(jsonInput, jsonOutputPath);
        System.out.println("JSON to CSV conversion completed. File saved at: " + jsonOutputPath);
    }
}