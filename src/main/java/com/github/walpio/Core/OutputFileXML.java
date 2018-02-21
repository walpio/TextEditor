package com.github.walpio.Core;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.support.AbstractXMLOutputProcessor;
import org.jdom2.output.support.FormatStack;
import org.jdom2.output.support.XMLOutputProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;

public class OutputFileXML {

    public static void main(String[] args) throws IOException {
        writeToXML();
    }

    public static void writeToXML() throws IOException {
        InputFile inputFile = new InputFile();
        Document doc = new Document();
        Element theRoot = new Element("text");
        doc.setRootElement(theRoot);
        for (int i = 0; i < inputFile.getListOfSortedWords().size(); i++) {
            Element sentence = new Element("sentence");
            theRoot.addContent(sentence);
            for (int j = 0; j < inputFile.getListOfSortedWords().get(i).size(); j++) {
                Element word = new Element("word");
                sentence.addContent(word);
                word.addContent(inputFile.getListOfSortedWords().get(i).get(j));
            }
        }
        XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat(), XMLOUTPUT);
        xmlOutput.output(doc, new FileOutputStream(new File(".\\Sample\\OutputXML.xml")));
        System.out.println("XML has been created");
    }

    public static final XMLOutputProcessor XMLOUTPUT = new AbstractXMLOutputProcessor() {
        @Override
        protected void printDeclaration(Writer out, FormatStack fstack) throws IOException {
            write(out, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            write(out, fstack.getLineSeparator());
        }
    };
}
