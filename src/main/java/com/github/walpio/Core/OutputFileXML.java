package com.github.walpio.Core;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputFileXML {

    public static void main(String[] args) throws IOException {
        writeToXML();
    }

    public static void writeToXML() throws IOException {
        InputFile inputFile = new InputFile();
        Document doc = new Document();
        Element theRoot = new Element("text");
        doc.setRootElement(theRoot);
        Element sentence = new Element("sentence");
        theRoot.addContent(sentence);
        Element word = new Element("word");
        sentence.addContent(word);
        word.addContent("word");
        XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
        xmlOutput.output(doc, new FileOutputStream(new File(".\\Sample\\OutputXML.xml")));
        System.out.println("XML has been created");
    }
}
