package com.github.walpio.Core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.*;

public class InputFile {

    private String filePath = ".\\Sample\\Sample Text.txt";
    private List<String> sentences = splitFileIntoSentences();
    private Map<String, List<String>> sortedWords = splitSentencesIntoWords();
    private List<List<String>> listOfSortedWords = splitSentencesIntoListOfWords();
    private int longestSentence = findTheLongestSentence();
    private int counter;

    public InputFile() throws IOException {
    }

    private String readFile() throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String inputText = "";
        String line = bufferedReader.readLine();
        counter = 1;
        System.out.println("Przeczytano linię: " + counter);
        while (line != null) {
            inputText += line;
            line = bufferedReader.readLine();
            counter++;
            System.out.println("Przeczytano linię: " + counter);
        }
        System.out.println("Przeczytano cały plik");
        counter = 1;
        return inputText;
    }

    private List<String> splitFileIntoSentences() throws IOException {
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

    private Map<String, List<String>> splitSentencesIntoWords() throws IOException {
        Map<String, List<String>> sortedWords = new LinkedHashMap<>();

        for (int i = 0; i < sentences.size(); i++) {
            String sentence = sentences.get(i);
            String key = String.format("Sentence %d", (i + 1));
            List<String> listOfWords = Arrays.asList(sentence.split("\\s+"));
            Collections.sort(listOfWords, String.CASE_INSENSITIVE_ORDER);
            sortedWords.put(key, listOfWords);
        }
        return sortedWords;
    }

    private List<List<String>> splitSentencesIntoListOfWords() throws IOException {
        List<List<String>> listOfSortedWords = new ArrayList<>();

        for (int i = 0; i < sentences.size(); i++) {
            String sentence = sentences.get(i);
            List<String> listOfWords = Arrays.asList(sentence.split("\\s+"));
            Collections.sort(listOfWords, String.CASE_INSENSITIVE_ORDER);
            listOfSortedWords.add(listOfWords);
        }
        System.out.println("Metoda splitSentenceIntoListOfWords wywołana: " + counter);
        counter++;
        return listOfSortedWords;
    }

    private int findTheLongestSentence() throws IOException {
        int longestSentence = 0;
        int numberOfWords = 0;
        for (Map.Entry<String, List<String>> entry : splitSentencesIntoWords().entrySet()) {
            List<String> value = entry.getValue();
            if (value.size() > numberOfWords) {
                numberOfWords = value.size();
                longestSentence = numberOfWords;
            }
        }
        System.out.println("Metoda findTheLongestSentence wywołana: " + counter);
        counter++;
        return longestSentence;
    }

    public Map<String, List<String>> getSortedWords() {
        return sortedWords;
    }

    public List<List<String>> getListOfSortedWords() {
        return listOfSortedWords;
    }

    public int getLongestSentence() {
        return longestSentence;
    }
}