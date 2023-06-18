package com.solvd.hospital.service.impl;

import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.service.IStAXService;
import com.solvd.hospital.util.stax.StAXDoctorParser;
import com.solvd.hospital.util.stax.StAXFileUpdate;
import com.solvd.hospital.util.stax.StAXNurseParser;
import com.solvd.hospital.util.stax.StaXPatientParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class StAXService implements IStAXService {
    private final static Logger LOGGER = LogManager.getLogger(StAXService.class);

    @Override
    public ArrayList<Doctor> getDoctorFromXML(String path) {
        StAXDoctorParser stAXParser = new StAXDoctorParser();
        return stAXParser.parse(path);
    }

    @Override
    public ArrayList<Nurse> getNurseFromXML(String path) {
        StAXNurseParser stAXNurseParser = new StAXNurseParser();
        return stAXNurseParser.parse(path);
    }

    @Override
    public ArrayList<Patient> getPatientFromXML(String path) {
        StaXPatientParser staXPatientParser = new StaXPatientParser();
        return staXPatientParser.parse(path);
    }

    @Override
    public void insertDoctorFromXML(Doctor doctor){
        if(doctor != null) {
            AppointmentService appointmentService = new AppointmentService();
            appointmentService.saveDoctorToDB(doctor);
        }else{
            LOGGER.error("Doctor object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void insertNurseFromXML(Nurse nurse){
        if(nurse != null) {
            AppointmentService appointmentService = new AppointmentService();
            appointmentService.saveNurseToDB(nurse);
        }else{
            LOGGER.error("Nurse object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void insertPatientFromXML(Patient patient){
        if(patient != null) {
            AppointmentService appointmentService = new AppointmentService();
            appointmentService.savePatientToDB(patient);
        }else{
            LOGGER.error("Patient object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updatePatientInXML(Patient patient, String path){
        if(patient != null) {
            StAXFileUpdate stAXFileUpdate = new StAXFileUpdate();
            stAXFileUpdate.updatePatient(patient, path);
        }else{
            LOGGER.error("Patient object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updateDoctorInXML(Doctor doctor, String path){
        if(doctor !=null) {
            StAXFileUpdate stAXFileUpdate = new StAXFileUpdate();
            stAXFileUpdate.updateDoctor(doctor, path);
        }else{
            LOGGER.error("Doctor object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void updateNurseInXML(Nurse nurse, String path){
        if(nurse != null) {
            StAXFileUpdate stAXFileUpdate = new StAXFileUpdate();
            stAXFileUpdate.updateNurse(nurse, path);
        }else{
            LOGGER.error("Nurse object is null.");
            throw new NullPointerException();
        }
    }

    @Override
    public void validate(String xmlPath, String xsdPath) {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema;
        FileInputStream xml;
        File xsd;
        try {
            xml = new FileInputStream(xmlPath);
            xsd = new File(xsdPath);
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found.");
            throw new RuntimeException(e);
        }
        try {
            schema = factory.newSchema(xsd);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        XMLStreamReader reader = null;
        Validator validator = schema.newValidator();
        try {
            reader = XMLInputFactory.newInstance().createXMLStreamReader(xml);
            validator.validate(new StAXSource(reader));
        } catch (XMLStreamException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
        }
        LOGGER.info("Document is valid");
    }


}
