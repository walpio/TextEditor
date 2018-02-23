package com.github.walpio.Core;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class OutputFileCSV {

    public static void main(String[] args) throws IOException {
        writeToCSV();
    }

    private static void writeToCSV() throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(".\\Sample\\OutputCSV.csv"));
        InputFile inputFile = new InputFile();

        String header;
        StringBuilder headerBuilder = new StringBuilder();
        for (int i = 1; i <= inputFile.getLongestSentence(); i++) {
            if (i == 1) {
                headerBuilder.append(",").append(" ").append("Word").append(" ").append(i).append(",").append(" ");
            } else if (i == (inputFile.getLongestSentence())) {
                headerBuilder.append("Word").append(" ").append(i);
            } else {
                headerBuilder.append("Word").append(" ").append(i).append(",").append(" ");
            }
        }
        header = headerBuilder.toString();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header).withQuote(null));

        for (Map.Entry<String, List<String>> entry : inputFile.getSortedWords().entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            String sentenceBody;
            StringBuilder sentenceBodyBuilder = new StringBuilder();

            for (int i = 0; i < value.size(); i++) {
                if (i == 0) {
                    sentenceBodyBuilder.append(" ").append(value.get(i)).append(",").append(" ");
                } else if (i == (value.size() - 1)) {
                    sentenceBodyBuilder.append(value.get(i));
                } else {
                    sentenceBodyBuilder.append(value.get(i)).append(",").append(" ");
                }
            }

            sentenceBody = sentenceBodyBuilder.toString();
            csvPrinter.printRecord(key, sentenceBody);
        }
        csvPrinter.flush();
        csvPrinter.close();
        System.out.println("CSV has been created");
    }
}