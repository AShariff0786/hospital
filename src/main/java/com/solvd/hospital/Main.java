package com.solvd.hospital;

import com.solvd.hospital.model.*;
import com.solvd.hospital.model.patient.MedicalBill;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.model.patient.PatientMedicalChart;
import com.solvd.hospital.model.patient.TreatmentData;
import com.solvd.hospital.service.IMedicalBillService;
import com.solvd.hospital.service.impl.AppointmentService;
import com.solvd.hospital.service.impl.MedicalBillService;
import com.solvd.hospital.service.impl.PatientMedicalChartService;
import com.solvd.hospital.service.impl.StAXService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        AppointmentService appointmentService = new AppointmentService();
        Doctor doctor = appointmentService.getDoctorInDB(1);
        LOGGER.info("Doctor: " + doctor.getId() + " " + doctor.getName());

        //Checks if XML is valid
        StAXService stAXService = new StAXService();
        stAXService.validate("src/main/resources/xml/doctors.xml", "src/main/resources/xsd/doctors.xsd");
        stAXService.validate("src/main/resources/xml/nurses.xml", "src/main/resources/xsd/nurses.xsd");
        stAXService.validate("src/main/resources/xml/patients.xml", "src/main/resources/xsd/patients.xsd");

        //Parses XML File
        ArrayList<Doctor> temp =  stAXService.getDoctorFromXML();
        temp.forEach(stAXService::insertDoctorFromXML);
        LOGGER.info("Doctor Id: " + temp.get(0).getId() + " Name: " + temp.get(0).getName()+ " " + temp.get(0).getPosition()
        + " DepartmentID :" + temp.get(0).getDepartment().getId() + " " + temp.get(0).getDepartment().getName());
        LOGGER.info("Doctor Id: " + temp.get(1).getId() + " Name: " + temp.get(1).getName()+ " " + temp.get(1).getPosition()
                + " DepartmentID :" + temp.get(1).getDepartment().getId() + " " + temp.get(1).getDepartment().getName());

        ArrayList<Nurse> nurses =  stAXService.getNurseFromXML();
        nurses.forEach(stAXService::insertNurseFromXML);
        LOGGER.info("Nurse Id: " + nurses.get(0).getId() + " Name: " + nurses.get(0).getName()+ " " + nurses.get(0).getPosition()
                + " DepartmentID :" + nurses.get(0).getDepartment().getId() + " " + nurses.get(0).getDepartment().getName());
        LOGGER.info("Nurse Id: " + nurses.get(1).getId() + " Name: " + nurses.get(1).getName()+ " " + nurses.get(1).getPosition()
                + " DepartmentID :" + nurses.get(1).getDepartment().getId() + " " + nurses.get(1).getDepartment().getName());


        ArrayList<Patient> patients =  stAXService.getPatientFromXML();
        patients.forEach(stAXService::insertPatientFromXML);
        LOGGER.info("Patient Id: " + patients.get(0).getId() + " Name: " + patients.get(0).getName()+ " " + patients.get(0).getDoctor().getName()
                + " Nurse Id:" + patients.get(0).getNurse().getName() + " " + patients.get(0).getChart().getDiagnosis()
                + " " + patients.get(0).getInsurance().getName());


        PatientMedicalChartService pmcService = new PatientMedicalChartService();
        PatientMedicalChart pmc = pmcService.getPatientMedicalChartInDB(1);
        LOGGER.info(pmc.getId() + " " + pmc.getDiagnosis());
        pmc.setDiagnosis("Broken Hand");
        pmcService.updatePatientMedicalChartInDB(pmc);
        pmc.setId(10);
        pmcService.savePatientMedicalChartInDB(pmc);
        pmcService.deletePatientMedicalChartInDB(pmc.getId());

        IMedicalBillService medicalBillService = new MedicalBillService();
        MedicalBill mb = medicalBillService.getMedicalBillInDB(1);
        mb.setAmountDue(1222.22);
        medicalBillService.updateMedicalBillInDB(mb);
        mb.setId(12);
        medicalBillService.saveMedicalBillInDB(mb);
        medicalBillService.deleteMedicalBillInDB(mb.getId());



        //Update XML File
        Doctor test = new Doctor(4);
        test.setName("Jimmy");
        test.setPosition("Physical Therapist");
        stAXService.updateDoctorInXML(test);

        MedicalBill medicalBill = medicalBillService.getMedicalBillInDB(1);
        Treatment treatment = new Treatment(1, "Surgery");
        treatment.setCost(123.11);
        TreatmentData treatmentData = new TreatmentData(medicalBill, treatment);
        String date = "2023-01-02";
        Date d;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d =sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        treatmentData.setStartOfTreatment(d);
        date = "2023-05-12";
        try {
            d =sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        treatmentData.setEndOfTreatment(d);
        treatmentData.getMedicalBill().getPatient().getInsurance().setName("Medicare");
        Department department = new Department(1,"Cardiology");
        treatmentData.getMedicalBill().getPatient().getDoctor().setDepartment(department);
        treatmentData.getMedicalBill().getPatient().getNurse().setDepartment(department);
        LocalTime time = LocalTime.parse("12:30");
        Appointment app1 = new Appointment(1, d, time );
        date = "2025-12-32";
        try {
            d =sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        time = LocalTime.parse("01:45");
        Appointment app2 = new Appointment(2, d, time );
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(app1);
        appointments.add(app2);
        treatmentData.getMedicalBill().getPatient().setAppointments(appointments);


        //Marshaller with JAXB
        try {
            JAXBContext context = JAXBContext.newInstance(TreatmentData.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(treatmentData, new File("src/main/resources/xml/treatmentdata.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        //Unmarshaller with JAXB
        TreatmentData tdata;
        try {
            JAXBContext context = JAXBContext.newInstance(TreatmentData.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            tdata = (TreatmentData) unmarshaller.unmarshal(new File("src/main/resources/xml/treatmentdata.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Unmarshaller test: " + tdata.getTreatment().getName() + " " + tdata.getTreatment().getCost()
            + tdata.getStartOfTreatment() + " " + tdata.getEndOfTreatment() + " " + tdata.getMedicalBill().getPatient().getName());
    }
}
