package com.solvd.hospital.util.stax;

import com.solvd.hospital.Main;
import com.solvd.hospital.model.Department;
import com.solvd.hospital.model.Nurse;
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
import java.util.ArrayList;

public class StAXNurseParser {
    private final static Logger LOGGER = LogManager.getLogger(StAXNurseParser.class);
    public ArrayList<Nurse> parse(){
        ArrayList<Nurse> nurses = new ArrayList<>();
        Department department = new Department();
        FileInputStream input = null;
        XMLEventReader reader = null;

        try {
            input = new FileInputStream("src/main/resources/xml/nurses.xml");
            reader = XMLInputFactory.newInstance().createXMLEventReader(input);
            Nurse nurse = null;
            while(reader.hasNext()){
                XMLEvent event = reader.nextEvent();
                if(event.isStartElement()){
                    StartElement startElement = event.asStartElement();
                    switch (startElement.getName().getLocalPart()){
                        case "nurse":
                            nurse = new Nurse();
                            Attribute employeeId = (startElement.getAttributeByName(new QName("employeeId")));
                            if(employeeId !=null){
                                nurse.setId(Integer.parseInt(employeeId.getValue()));
                            }
                            break;
                        case "name":
                            event = reader.nextEvent();
                            nurse.setName(event.asCharacters().getData());
                            break;
                        case "position":
                            event = reader.nextEvent();
                            nurse.setPosition(event.asCharacters().getData());
                            break;
                        case "department":
                            Attribute id = (startElement.getAttributeByName(new QName("id")));
                            if(id != null){
                                department.setId(Integer.parseInt(id.getValue()));
                            }
                            break;
                        case "depName":
                            event = reader.nextEvent();
                            department.setName(event.asCharacters().getData());
                            nurse.setDepartment(department);
                            break;
                    }
                }
                if(event.isEndElement()){
                    EndElement endElement = event.asEndElement();
                    if(endElement.getName().getLocalPart().equals("nurse")){
                        nurses.add(nurse);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found");
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            LOGGER.error("Unable to get XMLEventReader");
            throw new RuntimeException(e);
        }finally {
            try {
                input.close();
                reader.close();
            } catch (IOException e) {
                LOGGER.error("Unable to close file.");
                throw new RuntimeException(e);
            } catch (XMLStreamException e) {
                LOGGER.error("Unable to close XMLEventReader.");
                throw new RuntimeException(e);
            }
        }

        return nurses;
    }
}
