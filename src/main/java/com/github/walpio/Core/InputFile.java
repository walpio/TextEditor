package com.github.walpio.Core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class InputFile {

    private String filePath = ".\\Sample\\Sample Text.txt";

    public String readFile() throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String inputText = "";
        String line = bufferedReader.readLine();
        while (line != null) {
            inputText += line;
            line = bufferedReader.readLine();
        }
        return inputText;
    }

    public List<String> splitFileIntoSentences() throws IOException {
        BreakIterator breakIterator = BreakIterator.getSentenceInstance();
        List<String> sentences = new ArrayList<String>();

        String inputText = readFile();
        breakIterator.setText(inputText);
        int start = breakIterator.first();
        for (int end = breakIterator.next(); end != BreakIterator.DONE; start = end, end = breakIterator.next()) {
            String sentence = inputText.substring(start, end).trim();
            sentences.add(sentence);
        }
        return sentences;
    }
}
