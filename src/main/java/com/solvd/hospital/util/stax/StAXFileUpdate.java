package com.solvd.hospital.util.stax;

import com.solvd.hospital.Main;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;


public class StAXFileUpdate {
    private final static Logger LOGGER = LogManager.getLogger(StAXFileUpdate.class);
    public void updateDoctor(Doctor doctor) {
        String filePath = "src/main/resources/xml/doctors.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();

            // parse xml file and load into document
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            // update Element value
            updateDoctorElementValue(doc, doctor);

            // write the updated document to file or console
            writeXMLFile(doc, filePath);

        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
    public void updateNurse(Nurse nurse) {
        String filePath = "src/main/resources/xml/nurses.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();

            // parse xml file and load into document
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            // update Element value
            updateNurseElementValue(doc, nurse);

            // write the updated document to file or console
            writeXMLFile(doc, filePath);

        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
    public void updatePatient(Patient patient) {
        String filePath = "src/main/resources/xml/patients.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();

            // parse xml file and load into document
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            // update Element value
            updatePatientElementValue(doc, patient);

            // write the updated document to file or console
            writeXMLFile(doc, filePath);

        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }

    private static void writeXMLFile(Document doc, String path) {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("XML file updated successfully");
    }

    private static void updateDoctorElementValue(Document doc, Doctor doctor) {
        NodeList users = doc.getElementsByTagName("doctor");
        IntStream.range(0,users.getLength()).forEach(i ->{
            Element user = (Element) users.item(i);
            int id = Integer.parseInt(user.getAttribute("employeeId"));
            if(id == doctor.getId()){
                Node name = user.getElementsByTagName("name").item(0).getFirstChild();
                name.setNodeValue(doctor.getName());
                Node position = user.getElementsByTagName("position").item(0).getFirstChild();
                position.setNodeValue(doctor.getPosition());
            }
        });
    }
    private static void updateNurseElementValue(Document doc, Nurse nurse) {
        NodeList users = doc.getElementsByTagName("nurse");
        // loop for each user
        IntStream.range(0,users.getLength()).forEach(i ->{
            Element user = (Element) users.item(i);
            int id = Integer.parseInt(user.getAttribute("employeeId"));
            if(id == nurse.getId()){
                Node name = user.getElementsByTagName("name").item(0).getFirstChild();
                name.setNodeValue(nurse.getName());
                Node position = user.getElementsByTagName("position").item(0).getFirstChild();
                position.setNodeValue(nurse.getPosition());
            }
        });
    }

    private static void updatePatientElementValue(Document doc, Patient patient) {
        NodeList users = doc.getElementsByTagName("patient");
        IntStream.range(0,users.getLength()).forEach(i ->{
            Element user = (Element) users.item(i);
            int id = Integer.parseInt(user.getAttribute("id"));
            if(id == patient.getId()){
                Node name = user.getElementsByTagName("name").item(0).getFirstChild();
                name.setNodeValue(patient.getName());
                Node position = user.getElementsByTagName("address").item(0).getFirstChild();
                position.setNodeValue(patient.getAddress());
                Node phoneNumber = user.getElementsByTagName("phoneNumber").item(0).getFirstChild();
                phoneNumber.setNodeValue(patient.getPhoneNumber());
            }
        });
    }
    
}
