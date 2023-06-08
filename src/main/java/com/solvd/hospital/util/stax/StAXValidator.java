package com.solvd.hospital.util.stax;

import com.solvd.hospital.Main;
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
import java.io.IOException;

public class StAXValidator {
    private final static Logger LOGGER = LogManager.getLogger(StAXValidator.class);
    public void validate(String xmlPath, String xsdPath){
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema = null;
        try {
            schema = factory.newSchema(new File(xsdPath));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        XMLStreamReader reader = null;
        Validator validator = schema.newValidator();
        try {
            reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(xmlPath));
            validator.validate(new StAXSource(reader));
        } catch (XMLStreamException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
        }
        //no exception thrown, so valid
        LOGGER.info("Document is valid");
    }
}
