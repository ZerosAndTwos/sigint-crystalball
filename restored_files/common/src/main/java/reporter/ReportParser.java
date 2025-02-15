package reporter;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReportParser {

    private ReportResults results = new ReportResults();
    private Logger log = Logger.getLogger(ReportParser.class);

    public ReportResults parseXmlReportFiles(String pathToXml){
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(pathToXml), "*.xml")) {
            for (Path file: stream) {
                if(!file.toFile().isDirectory()) {

                    log.debug("Parse file: " + file.getFileName());

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(new File(String.valueOf(file)));

                    doc.getDocumentElement().normalize();

                    NodeList testList = doc.getElementsByTagName("test-case");
                    if (testList.getLength() != 0) {
                        for (int i = 0; i < testList.getLength(); i++){
                            Node testNode = testList.item(i);
                            TestCase testCase = new TestCase();
                            if (testNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element testElement = (Element) testNode;
                                testCase.setStatus(testElement.getAttribute("status"));
                                NodeList nameList = testElement.getElementsByTagName("name");
                                Node nameNode = nameList.item(0);
                                if(nameNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element nameElement = (Element) nameNode;
                                    testCase.setTitle(nameElement.getTextContent());
                                    log.debug("Scenario: " + testCase.getTitle());
                                }
                                NodeList stepList = testElement.getElementsByTagName("step");
                                if (stepList.getLength() != 0) {
                                    for (int j = 0; j < stepList.getLength(); j++){
                                        Node stepNode = stepList.item(j);
                                        Step step = new Step().setNumber(j+1);
                                        if (testNode.getNodeType() == Node.ELEMENT_NODE) {
                                            Element stepElement = (Element) stepNode;
                                            step.setStatus(stepElement.getAttribute("status"));
                                            NodeList stepNameList = stepElement.getElementsByTagName("name");
                                            Node stepNameNode = stepNameList.item(0);
                                            if(stepNameNode.getNodeType() == Node.ELEMENT_NODE) {
                                                Element stepNameElement = (Element) stepNameNode;
                                                step.setName(stepNameElement.getTextContent());
                                                log.debug("step: " + step.getName() + ", status: " + step.getStatus());
                                            }
                                        }
                                        Step updatedStep = testCase.add(step);
                                        if (updatedStep != null) {
                                            log.warn("Step Γäû" + step.getNumber() + ". " + updatedStep.getName() + ", was updated");
                                        }
                                    }
                                }
                                TestCase updatedTest = results.add(testCase);
                                if (updatedTest != null) {
                                    log.warn("Test: " + updatedTest.getTitle() + ", was updated");
                                }
                            }
                        }
                    } else {
                        log.debug("file: " + file.getFileName() + ", not contain any tests");
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return results;
    }

    public ReportResults getResults() {
        return results;
    }
}
