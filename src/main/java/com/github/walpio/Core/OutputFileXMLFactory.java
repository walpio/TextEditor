package com.github.walpio.Core;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputFileXMLFactory {
    public static void writeToXMLFactory(InputFile inputFile) throws ParserConfigurationException, IOException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document xmlDoc = docBuilder.newDocument();
        xmlDoc.setXmlStandalone(true);

        Element theRoot = xmlDoc.createElement("text");
        xmlDoc.appendChild(theRoot);
        for (int i = 0; i < inputFile.getListOfSortedWords().size(); i++) {
            Element sentence = xmlDoc.createElement("sentence");
            theRoot.appendChild(sentence);
            for (int j = 0; j < inputFile.getListOfSortedWords().get(i).size(); j++) {
                Element word = xmlDoc.createElement("word");
                sentence.appendChild(word);
                Text wordBody = xmlDoc.createTextNode(inputFile.getListOfSortedWords().get(i).get(j));
                word.appendChild(wordBody);
            }
        }

        OutputFormat outFormat = new OutputFormat(xmlDoc);
        outFormat.setStandalone(true);
        outFormat.setIndenting(true);

        File xmlFile = new File(".\\Sample\\OutputXMLFactory.xml");
        FileOutputStream outStream = new FileOutputStream(xmlFile);

        XMLSerializer serializer = new XMLSerializer(outStream, outFormat);
        serializer.serialize(xmlDoc);
        outStream.flush();
        outStream.close();
        System.out.println("XML has been factored");
    }
}