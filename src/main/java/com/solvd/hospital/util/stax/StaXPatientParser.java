package com.solvd.hospital.util.stax;

import com.solvd.hospital.model.Department;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Insurance;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.model.patient.PatientMedicalChart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StaXPatientParser {
    private final static Logger LOGGER = LogManager.getLogger(StaXPatientParser.class);
    public ArrayList<Patient> parse(){
        ArrayList<Patient> patients = new ArrayList<>();
        Department docDepartment = new Department();
        Department nurseDepartment = new Department();
        Doctor doctor = new Doctor();
        Nurse nurse = new Nurse();
        PatientMedicalChart pmc = new PatientMedicalChart();
        Insurance insurance = new Insurance();
        XMLEventReader reader = null;


        try (FileInputStream input = new FileInputStream("src/main/resources/xml/patients.xml");) {
            reader = XMLInputFactory.newInstance().createXMLEventReader(input);
            Patient patient = null;
            while(reader.hasNext()){
                XMLEvent event = reader.nextEvent();
                if(event.isStartElement()){
                    StartElement startElement = event.asStartElement();
                    switch (startElement.getName().getLocalPart()){
                        case "patient":
                            patient = new Patient();
                            Attribute employeeId = (startElement.getAttributeByName(new QName("id")));
                            if(employeeId !=null){
                                patient.setId(Integer.parseInt(employeeId.getValue()));
                            }
                            break;
                        case "name":
                            event = reader.nextEvent();
                            if(patient != null) {
                                patient.setName(event.asCharacters().getData());
                            }
                            break;
                        case "address":
                            event = reader.nextEvent();
                            if(patient != null) {
                                patient.setAddress(event.asCharacters().getData());
                            }
                            break;
                        case "phoneNumber":
                            event = reader.nextEvent();
                            if(patient != null) {
                                patient.setPhoneNumber(event.asCharacters().getData());
                            }
                            break;
                        case "doctor":
                            Attribute docId = (startElement.getAttributeByName(new QName("employeeId")));
                            if(docId != null){
                                doctor.setId(Integer.parseInt(docId.getValue()));
                            }
                            break;
                        case "docName":
                            event = reader.nextEvent();
                            doctor.setName(event.asCharacters().getData());
                            break;
                        case "docPosition":
                            event = reader.nextEvent();
                            doctor.setPosition(event.asCharacters().getData());
                            break;
                        case "docDepartment":
                            Attribute depId = (startElement.getAttributeByName(new QName("id")));
                            if(depId != null){
                                docDepartment.setId(Integer.parseInt(depId.getValue()));
                            }
                            break;
                        case "docDepartmentName":
                            event = reader.nextEvent();
                            docDepartment.setName(event.asCharacters().getData());
                            doctor.setDepartment(docDepartment);
                            if(patient != null) {
                                patient.setDoctor(doctor);
                            }
                            break;
                        case "patientMedicalChart":
                            Attribute reportId = (startElement.getAttributeByName(new QName("reportId")));
                            if(reportId != null){
                                pmc.setId(Integer.parseInt(reportId.getValue()));
                            }
                            break;
                        case "diagnosis":
                            event = reader.nextEvent();
                            pmc.setDiagnosis(event.asCharacters().getData());
                            break;
                        case "dateVisited":
                            event = reader.nextEvent();
                            String date = event.asCharacters().getData();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                pmc.setDate(sdf.parse(date));
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            if(patient != null) {
                                patient.setChart(pmc);
                            }
                            break;
                        case "nurse":
                            Attribute nurseId = (startElement.getAttributeByName(new QName("employeeId")));
                            if(nurseId != null){
                                nurse.setId(Integer.parseInt(nurseId.getValue()));
                            }
                            break;
                        case "nurseName":
                            event = reader.nextEvent();
                            nurse.setName(event.asCharacters().getData());
                            break;
                        case "nursePosition":
                            event = reader.nextEvent();
                            nurse.setPosition(event.asCharacters().getData());
                            break;
                        case "nurseDepartment":
                            Attribute nurDepId = (startElement.getAttributeByName(new QName("id")));
                            if(nurDepId != null){
                                nurseDepartment.setId(Integer.parseInt(nurDepId.getValue()));
                            }
                            break;
                        case "nurseDepartmentName":
                            event = reader.nextEvent();
                            nurseDepartment.setName(event.asCharacters().getData());
                            nurse.setDepartment(nurseDepartment);
                            if(patient != null) {
                                patient.setNurse(nurse);
                            }
                            break;
                        case "insurance":
                            Attribute insurId = (startElement.getAttributeByName(new QName("id")));
                            if(insurId != null){
                                insurance.setId(Integer.parseInt(insurId.getValue()));
                            }
                            break;
                        case "insuranceName":
                            event = reader.nextEvent();
                            insurance.setName(event.asCharacters().getData());
                            if(patient != null) {
                                patient.setInsurance(insurance);
                            }
                            break;

                    }
                }
                if(event.isEndElement()){
                    EndElement endElement = event.asEndElement();
                    if(endElement.getName().getLocalPart().equals("patient")){
                        patients.add(patient);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found.");
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            LOGGER.error("Unable to obtain XMLEventReader.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                LOGGER.error("Unable to close XMLEventReader.");
                throw new RuntimeException(e);
            }
        }

        return patients;
    }
}
