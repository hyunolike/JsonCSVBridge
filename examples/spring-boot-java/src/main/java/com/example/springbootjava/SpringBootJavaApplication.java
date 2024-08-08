package com.example.springbootjava;

import com.jsoncsvbridge.csv.CsvCreator;
import com.jsoncsvbridge.csv.MergeCsvCreator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import static com.jsoncsvbridge.factory.DefaultCsvCreatorFactory.*;

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

        String jsonOutputPath = new File(outputDir, "output_json.csv").getAbsolutePath();
        String mergeJsonOutputPath = new File(outputDir, "output_merge_json.csv").getAbsolutePath();

        // JSON to CSV
        // 기능1. JSON 형식 문자열 CSV 파일로 변환
        CsvCreator jsonCreator = generateCsv("json");

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

        // 기능2. 2개 JSON 형식 문자열 CSV 파일로 변환
        CsvCreator mergeJsonCreator = generateMergeCsv();

        String data1 = """
                    [
                        {"name": "John", "age": 30, "city": "New York", "country": "USA"}
                    ]
                """;
        String data2 = """
                    [
                        {"name": "Alice", "age": 25, "city": "London", "occupation": "Engineer"},
                        {"name": "Bob", "age": 35, "city": "Paris", "hobby": "Photography"}
                    ]
                """;

        ((MergeCsvCreator) mergeJsonCreator).createMergedCsv(data1, data2, mergeJsonOutputPath);
        System.out.println("Merged JSON to CSV conversion completed. File saved at: " + mergeJsonOutputPath);
    }
}