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
        String ciEnvironment = System.getProperty("CI");
        String testExecution = System.getProperty("maven.test.skip");
        
        if ("true".equals(ciEnvironment) || "true".equals(testExecution)) {
            System.out.println("Skipping regression suite update in CI/test environment for: " + testClassName);
            return;
        }
        
        String fullClassName = "com.cutanddry.qa.tests." + packageName + "." + testClassName;
        String suiteFile = findBestRegressionSuite(projectRoot);
        
        if (suiteFile != null) {
            try {
                addClassToSuite(suiteFile, fullClassName);
                System.out.println("Added " + fullClassName + " to " + suiteFile);
            } catch (Exception e) {
                System.err.println("Failed to add test to suite: " + e.getMessage());
                System.out.println("Continuing without updating regression suite");
            }
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
    
    private static void addClassToSuite(String suiteFile, String fullClassName) throws Exception {
        File file = new File(suiteFile);
        if (!file.exists() || !file.canWrite()) {
            throw new Exception("Suite file does not exist or is not writable: " + suiteFile);
        }
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        
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
            
            NodeList existingClasses = classesElement.getElementsByTagName("class");
            for (int i = 0; i < existingClasses.getLength(); i++) {
                Element existingClass = (Element) existingClasses.item(i);
                if (fullClassName.equals(existingClass.getAttribute("name"))) {
                    System.out.println("Class " + fullClassName + " already exists in suite, skipping");
                    return;
                }
            }
            
            Element newClassElement = doc.createElement("class");
            newClassElement.setAttribute("name", fullClassName);
            classesElement.appendChild(newClassElement);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } else {
            throw new Exception("No test nodes found in suite file: " + suiteFile);
        }
    }
}
