package com.github.walpio.Core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.*;

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
        List<String> sentences = new ArrayList<>();

        String inputText = readFile();
        breakIterator.setText(inputText);
        int start = breakIterator.first();
        for (int end = breakIterator.next();
             end != BreakIterator.DONE;
             start = end, end = breakIterator.next()) {
            String sentence = inputText.substring(start, end).trim();
            sentences.add(sentence);
        }
        return sentences;
    }

    public Map<String, List<String>> splitSentencesIntoWords() throws IOException {
        Map<String, List<String>> sortedWords = new LinkedHashMap<>();

        for (int i = 0; i < splitFileIntoSentences().size(); i++) {
            String sentence = splitFileIntoSentences().get(i);
            String key = String.format("Sentence %d", (i + 1));
            List<String> listOfWords = Arrays.asList(sentence.split("\\s+"));
            Collections.sort(listOfWords, String.CASE_INSENSITIVE_ORDER);
            sortedWords.put(key, listOfWords);
        }
        return sortedWords;
    }

    public List<List<String>> splitSentencesIntoListOfWords() throws IOException {
        List<List<String>> sortedWords = new ArrayList<>();

        String sentence;
        for (int i = 0; i < splitFileIntoSentences().size(); i++) {
            sentence = splitFileIntoSentences().get(i);
            List<String> listOfWords = Arrays.asList(sentence.split("\\s+"));
            Collections.sort(listOfWords, String.CASE_INSENSITIVE_ORDER);
            sortedWords.add(listOfWords);
        }
        return sortedWords;
    }

    public int findLongestSentence() throws IOException {
        int numberOfWords = 0;
        int longestSentence = 0;
        for (Map.Entry<String, List<String>> entry : splitSentencesIntoWords().entrySet()) {
            List<String> value = entry.getValue();
            if (value.size() > longestSentence) {
                longestSentence = value.size();
                numberOfWords = longestSentence;
            }
        }
        return numberOfWords;
    }

}
