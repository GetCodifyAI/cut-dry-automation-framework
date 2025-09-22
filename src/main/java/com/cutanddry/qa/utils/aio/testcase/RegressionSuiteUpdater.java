package com.cutanddry.qa.utils.aio.testcase;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RegressionSuiteUpdater {
    
    public static void addTestToSuite(String testClassName, String packageName, String projectRoot) {
        String fullClassName = "com.cutanddry.qa.tests." + packageName + "." + testClassName;
        String suiteFile = findBestRegressionSuite(projectRoot);
        
        if (suiteFile != null) {
            addClassToSuite(suiteFile, fullClassName);
            System.out.println("Added " + fullClassName + " to " + suiteFile);
        } else {
            System.err.println("Could not find suitable regression suite file");
        }
    }
    
    private static String findBestRegressionSuite(String projectRoot) {
        List<String> suiteFiles = new ArrayList<>();
        
        for (int i = 1; i <= 13; i++) {
            String fileName = projectRoot + "/regression" + i + ".xml";
            File file = new File(fileName);
            if (file.exists()) {
                suiteFiles.add(fileName);
            }
        }
        
        if (suiteFiles.isEmpty()) {
            return null;
        }
        
        String smallestSuite = suiteFiles.get(0);
        int smallestClassCount = countClassesInSuite(smallestSuite);
        
        for (String suiteFile : suiteFiles) {
            int classCount = countClassesInSuite(suiteFile);
            if (classCount < smallestClassCount) {
                smallestClassCount = classCount;
                smallestSuite = suiteFile;
            }
        }
        
        return smallestSuite;
    }
    
    private static int countClassesInSuite(String suiteFile) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(suiteFile));
            
            NodeList classes = doc.getElementsByTagName("class");
            return classes.getLength();
            
        } catch (Exception e) {
            System.err.println("Error counting classes in " + suiteFile + ": " + e.getMessage());
            return Integer.MAX_VALUE;
        }
    }
    
    private static void addClassToSuite(String suiteFile, String fullClassName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(suiteFile));
            
            NodeList testNodes = doc.getElementsByTagName("test");
            if (testNodes.getLength() > 0) {
                Element testElement = (Element) testNodes.item(0);
                
                NodeList classesNodes = testElement.getElementsByTagName("classes");
                Element classesElement;
                
                if (classesNodes.getLength() > 0) {
                    classesElement = (Element) classesNodes.item(0);
                } else {
                    classesElement = doc.createElement("classes");
                    testElement.appendChild(classesElement);
                }
                
                Element newClassElement = doc.createElement("class");
                newClassElement.setAttribute("name", fullClassName);
                classesElement.appendChild(newClassElement);
                
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(suiteFile));
                transformer.transform(source, result);
            }
            
        } catch (Exception e) {
            System.err.println("Error updating suite file " + suiteFile + ": " + e.getMessage());
        }
    }
}
