package com.github.walpio.Core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
}
