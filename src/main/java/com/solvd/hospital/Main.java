package com.solvd.hospital;


import com.solvd.hospital.model.Appointment;
import com.solvd.hospital.model.Department;
import com.solvd.hospital.model.Doctor;
import com.solvd.hospital.model.Insurance;
import com.solvd.hospital.model.Nurse;
import com.solvd.hospital.model.Treatment;
import com.solvd.hospital.model.patient.MedicalBill;
import com.solvd.hospital.model.patient.Patient;
import com.solvd.hospital.model.patient.PatientMedicalChart;
import com.solvd.hospital.model.patient.TreatmentData;
import com.solvd.hospital.service.IMedicalBillService;
import com.solvd.hospital.service.impl.AppointmentService;
import com.solvd.hospital.service.impl.JAXBService;
import com.solvd.hospital.service.impl.JacksonService;
import com.solvd.hospital.service.impl.MedicalBillService;
import com.solvd.hospital.service.impl.PatientMedicalChartService;
import com.solvd.hospital.service.impl.StAXService;
import com.solvd.hospital.util.PropertiesUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);
    private final static String docFile = "src/main/resources/json/doctor.json";
    private final static String docXMLFile = "src/main/resources/xml/doctors.xml";
    private final static String docXSDFile = "src/main/resources/xsd/doctors.xsd";
    private final static String nurseFile = "src/main/resources/json/nurse.json";
    private final static String nurseXMLFile = "src/main/resources/xml/nurses.xml";
    private final static String nurseXSDFile = "src/main/resources/xsd/nurses.xsd";
    private final static String patientFile = "src/main/resources/json/patient.json";
    private final static String patientXMLFile = "src/main/resources/xml/patients.xml";
    private final static String patientXSDFile = "src/main/resources/xsd/patients.xsd";
    private final static String insuranceFile = "src/main/resources/json/insurance.json";
    private final static String tDataXMLFile = "src/main/resources/xml/treatmentdata.xml";
    private final static String tDataFile = "src/main/resources/json/treatmentdata.json";
    private final static String mybatisConfig = "mybatis_config.xml";
    public static void main(String[] args) {
        AppointmentService appointmentService = new AppointmentService();
        Doctor doctor = appointmentService.getDoctorInDB(1);
        LOGGER.info("Doctor: " + doctor.getId() + " " + doctor.getName());
        /*Appointment appointment1 = appointmentService.getAppointmentInDB(1);
        appointment1.setDoctor(doctor);
        appointmentService.updateAppointmentInDB(appointment1);
        appointment1.setDoctor(doctor);
        appointment1.setId(2);
        appointmentService.saveAppointmentToDB(appointment1);
        Nurse nurse1 = appointmentService.getNurseInDB(1);
*/
        //Checks if XML is valid
        StAXService stAXService = new StAXService();
        stAXService.validate(docXMLFile, docXSDFile);
        stAXService.validate(nurseXMLFile, nurseXSDFile);
        stAXService.validate(patientXMLFile, patientXSDFile);

        //Parses XML File
        ArrayList<Doctor> temp =  stAXService.getDoctorFromXML(docXMLFile);
        //temp.forEach(stAXService::insertDoctorFromXML);
        LOGGER.info("Doctor Id: " + temp.get(0).getId() + " Name: " + temp.get(0).getName()+ " " + temp.get(0).getPosition()
        + " DepartmentID :" + temp.get(0).getDepartment().getId() + " " + temp.get(0).getDepartment().getName());
        LOGGER.info("Doctor Id: " + temp.get(1).getId() + " Name: " + temp.get(1).getName()+ " " + temp.get(1).getPosition()
                + " DepartmentID :" + temp.get(1).getDepartment().getId() + " " + temp.get(1).getDepartment().getName());
        temp.get(0).setPosition("Neurologist");
        appointmentService.updateDoctorInDB(temp.get(0));

        ArrayList<Nurse> nurses =  stAXService.getNurseFromXML(nurseXMLFile);
        //nurses.forEach(stAXService::insertNurseFromXML);
        LOGGER.info("Nurse Id: " + nurses.get(0).getId() + " Name: " + nurses.get(0).getName()+ " " + nurses.get(0).getPosition()
                + " DepartmentID :" + nurses.get(0).getDepartment().getId() + " " + nurses.get(0).getDepartment().getName());
        LOGGER.info("Nurse Id: " + nurses.get(1).getId() + " Name: " + nurses.get(1).getName()+ " " + nurses.get(1).getPosition()
                + " DepartmentID :" + nurses.get(1).getDepartment().getId() + " " + nurses.get(1).getDepartment().getName());
        nurses.get(0).setPosition("Medical Assistant");
        appointmentService.updateNurseInDB(nurses.get(0));

        ArrayList<Patient> patients =  stAXService.getPatientFromXML(patientXMLFile);
        //patients.forEach(stAXService::insertPatientFromXML);
        LOGGER.info("Patient Id: " + patients.get(0).getId() + " Name: " + patients.get(0).getName()+ " " + patients.get(0).getDoctor().getName()
                + " Nurse Id:" + patients.get(0).getNurse().getName() + " " + patients.get(0).getChart().getDiagnosis()
                + " " + patients.get(0).getInsurance().getName());
        patients.get(0).setAddress("1111-111");
        appointmentService.updatePatientInDB(patients.get(0));


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
        //stAXService.updateDoctorInXML(test, docXMLFile);

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
        treatmentData.getMedicalBill().getPatient().getDoctor().setPosition("Surgeon");
        treatmentData.getMedicalBill().getPatient().getNurse().setPosition("Medical Assistant");


        //Marshaller with JAXB
        JAXBService<TreatmentData> tdataParsingService = new JAXBService<>();
        tdataParsingService.marshal(treatmentData, tDataXMLFile, TreatmentData.class.getName());


        //Unmarshaller with JAXB
        TreatmentData tdata = tdataParsingService.unmarshal(tDataXMLFile, TreatmentData.class.getName());

        LOGGER.info("Unmarshaller test: " + tdata.getTreatment().getName() + " " + tdata.getTreatment().getCost()
           + tdata.getStartOfTreatment() + " " + tdata.getEndOfTreatment() + " " + tdata.getMedicalBill().getPatient().getName());

        //Serialize with Jackson
        JacksonService<TreatmentData> tdataJackson = new JacksonService<>();
        tdataJackson.serialize(tdata, tDataFile);
        JacksonService<Doctor> doctorJacksonService = new JacksonService<>();
        JacksonService<Nurse> nurseJacksonService = new JacksonService<>();
        JacksonService<Patient> patientJacksonService = new JacksonService<>();
        JacksonService<Insurance> insuranceJacksonService = new JacksonService<>();
        doctorJacksonService.serialize(temp.get(0), docFile);
        nurseJacksonService.serialize(nurses.get(0), nurseFile);
        patientJacksonService.serialize(patients.get(0), patientFile);
        insuranceJacksonService.serialize(patients.get(0).getInsurance(), insuranceFile);

        //Deserialize with Jackson
        Insurance insurance = insuranceJacksonService.deserialize(insuranceFile, Insurance.class.getName());
        Doctor doc = doctorJacksonService.deserialize(docFile, Doctor.class.getName());
        TreatmentData tdata2 = tdataJackson.deserialize(tDataFile,  TreatmentData.class.getName());
        Patient patient2 = patientJacksonService.deserialize(patientFile, Patient.class.getName());
        Nurse nurse2 = nurseJacksonService.deserialize(nurseFile, Nurse.class.getName());
        LOGGER.info("Deserializtion with Jackson on Insurance: " + insurance.getId() + " " +insurance.getName() );
        LOGGER.info("Deserializtion with Jackson on Doctor: " + doc.getId() + " " +doc.getName() );
        LOGGER.info("Deserializtion with Jackson on Patient: " + patient2.getId() + " " +patient2.getName() );
        LOGGER.info("Deserializtion with Jackson on Nurse: " + nurse2.getId() + " " +nurse2.getName() );
        LOGGER.info("Deserializtion with Jackson on TreatmentData: " + tdata2.getStartOfTreatment() + " " +tdata2.getEndOfTreatment() );

        com.solvd.hospital.service.mybatisimpl.AppointmentService appbatis = new com.solvd.hospital.service.mybatisimpl.AppointmentService();
        Doctor doctor1 = appbatis.getDoctorInDB(1);
        LOGGER.info("MAPPER: " + doctor1.getName() + " " +doctor1.getId() + " " + doctor1.getPosition() +
                " " + doctor1.getDepartment().getName());
        doctor1.setName("Melo");
        appbatis.updateDoctorInDB(doctor1);
        doctor1.setId(12);
        appbatis.saveDoctorToDB(doctor1);
        appbatis.deleteDoctorInDB(12);

        Patient patient = appbatis.getPatientInDB(1);
        LOGGER.info("MAPPER: " + patient.getName()  + " " + patient.getAddress() + " " + patient.getPhoneNumber()
                + " " +patient.getDoctor().getName() + " "+ patient.getDoctor().getDepartment().getName()+ " "
                + patient.getNurse().getName() + " " + patient.getNurse().getDepartment().getName() +
                " "  + patient.getInsurance().getName() + " " + patient.getChart().getDiagnosis());

    }
}
