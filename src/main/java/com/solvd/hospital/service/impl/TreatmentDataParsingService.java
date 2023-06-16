package com.solvd.hospital.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.hospital.Main;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Insurance;
import com.solvd.hospital.model.patient.TreatmentData;
import com.solvd.hospital.service.ITreatmentDataParsingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class TreatmentDataParsingService implements ITreatmentDataParsingService {
    private final static Logger LOGGER = LogManager.getLogger(TreatmentDataParsingService.class);
    @Override
    public void marshalTreatmentData(TreatmentData treatmentData, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(TreatmentData.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(treatmentData, file);
        } catch (JAXBException e) {
            LOGGER.error("Error trying to marshal.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public TreatmentData unmarshalTreatmentData(File file) {
        TreatmentData tdata;
        try {
            JAXBContext context = JAXBContext.newInstance(TreatmentData.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            tdata = (TreatmentData) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            LOGGER.error("Error trying to unmarshal.");
            throw new RuntimeException(e);
        }
        return tdata;
    }

    @Override
    public void serializeTreatmentData(TreatmentData treatmentData, File file) {
        ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,treatmentData);
        } catch (IOException e) {
            LOGGER.error("File not found.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public TreatmentData deserializeTreatmentData(File file) {
        TreatmentData treatmentData;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        try {
            treatmentData = objectMapper.readValue(file, TreatmentData.class);
        } catch (IOException e) {
            LOGGER.error("File not found.");
            throw new RuntimeException(e);
        }
        return treatmentData;
    }
}
