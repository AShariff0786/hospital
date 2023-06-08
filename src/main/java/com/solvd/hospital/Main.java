package com.solvd.hospital;

import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.service.AppointmentService;
import com.solvd.hospital.service.StAXService;
import com.solvd.hospital.util.stax.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        AppointmentService appointmentService = new AppointmentService();
        Doctor doctor = appointmentService.getDoctorInDB(1);
        LOGGER.info("Doctor: " + doctor.getId() + " " + doctor.getName());

        //Checks if XML is valid
        StAXValidator validator = new StAXValidator();
        validator.validate("src/main/resources/xml/doctors.xml", "src/main/resources/xsd/doctors.xsd");
        validator.validate("src/main/resources/xml/nurses.xml", "src/main/resources/xsd/nurses.xsd");
        validator.validate("src/main/resources/xml/patients.xml", "src/main/resources/xsd/patients.xsd");

        //Parses XML File
        StAXDoctorParser stAXParser = new StAXDoctorParser();
        ArrayList<Doctor> temp =  stAXParser.parse();
        StAXService stAXService = new StAXService();
        temp.stream().forEach(doc -> {
            stAXService.insertDoctorFromXML(doc);
        });
        LOGGER.info("Doctor Id: " + temp.get(0).getId() + " Name: " + temp.get(0).getName()+ " " + temp.get(0).getPosition()
        + " DepartmentID :" + temp.get(0).getDepartment().getId() + " " + temp.get(0).getDepartment().getName());
        LOGGER.info("Doctor Id: " + temp.get(1).getId() + " Name: " + temp.get(1).getName()+ " " + temp.get(1).getPosition()
                + " DepartmentID :" + temp.get(1).getDepartment().getId() + " " + temp.get(1).getDepartment().getName());

        StAXNurseParser stAXNurseParser = new StAXNurseParser();
        ArrayList<Nurse> nurses =  stAXNurseParser.parse();
        nurses.stream().forEach(nurse -> {
            stAXService.insertNurseFromXML(nurse);
        });
        LOGGER.info("Nurse Id: " + nurses.get(0).getId() + " Name: " + nurses.get(0).getName()+ " " + nurses.get(0).getPosition()
                + " DepartmentID :" + nurses.get(0).getDepartment().getId() + " " + nurses.get(0).getDepartment().getName());
        LOGGER.info("Nurse Id: " + nurses.get(1).getId() + " Name: " + nurses.get(1).getName()+ " " + nurses.get(1).getPosition()
                + " DepartmentID :" + nurses.get(1).getDepartment().getId() + " " + nurses.get(1).getDepartment().getName());


        StaXPatientParser stAXPatientParser = new StaXPatientParser();
        ArrayList<Patient> patients =  stAXPatientParser.parse();
        patients.stream().forEach(patient -> {
            stAXService.insertPatientFromXML(patient);
        });
        LOGGER.info("Patient Id: " + patients.get(0).getId() + " Name: " + patients.get(0).getName()+ " " + patients.get(0).getDoctor().getName()
                + " Nurse Id:" + patients.get(0).getNurse().getName() + " " + patients.get(0).getChart().getDiagnosis()
                + " " + patients.get(0).getInsurance().getName());


        //Update XML File
        Doctor test = new Doctor(4);
        test.setName("Jimmy");
        test.setPosition("Physical Therapist");
        StAXFileUpdate update = new StAXFileUpdate();
        update.updateDoctor(test);

    }
}
