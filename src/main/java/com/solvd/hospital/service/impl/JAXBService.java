package com.solvd.hospital.service.impl;

import com.solvd.hospital.model.patient.TreatmentData;
import com.solvd.hospital.service.IJAXBService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBService<T> implements IJAXBService<T> {
    private final static Logger LOGGER = LogManager.getLogger(JAXBService.class);

    @Override
    public void marshal(T t, String file, String className) {
        Class<T> tClass;
        try {
            tClass = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            JAXBContext context = JAXBContext.newInstance(tClass);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(t, new File(file));
        } catch (JAXBException e) {
            LOGGER.error("Error trying to marshal.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public T unmarshal(String file, String className) {
        T t;
        Class<T> tClass;
        try {
            tClass = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            JAXBContext context = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new File (file));
        } catch (JAXBException e) {
            LOGGER.error("Error trying to unmarshal.");
            throw new RuntimeException(e);
        }
        return t;
    }
}
