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
import com.solvd.hospital.service.mybatisimpl.AppointmentService;
import com.solvd.hospital.service.impl.JAXBService;
import com.solvd.hospital.service.impl.JacksonService;
import com.solvd.hospital.service.mybatisimpl.MedicalBillService;
import com.solvd.hospital.service.mybatisimpl.PatientMedicalChartService;
import com.solvd.hospital.service.impl.StAXService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);
    private final static String DOC_FILE = "src/main/resources/json/doctor.json";
    private final static String DOC_XML_FILE = "src/main/resources/xml/doctors.xml";
    private final static String DOC_XSD_FILE = "src/main/resources/xsd/doctors.xsd";
    private final static String NURSE_FILE = "src/main/resources/json/nurse.json";
    private final static String NURSE_XML_FILE = "src/main/resources/xml/nurses.xml";
    private final static String NURSE_XSD_FILE = "src/main/resources/xsd/nurses.xsd";
    private final static String PATIENT_FILE = "src/main/resources/json/patient.json";
    private final static String PATIENT_XML_FILE = "src/main/resources/xml/patients.xml";
    private final static String PATIENT_XSD_FILE = "src/main/resources/xsd/patients.xsd";
    private final static String INSURANCE_FILE = "src/main/resources/json/insurance.json";
    private final static String TDATA_XML_FILE = "src/main/resources/xml/treatmentdata.xml";
    private final static String TDATA_FILE = "src/main/resources/json/treatmentdata.json";
    private final static String MYBATIS_CONFIG = "mybatis_config.xml";
    public static void main(String[] args) {
        AppointmentService appointmentService = new AppointmentService();
        Doctor doctor = appointmentService.getDoctorInDB(1);
        LOGGER.info("Doctor: " + doctor.getId() + " " + doctor.getName());
        Appointment appointment1 = appointmentService.getAppointmentInDB(1);
        appointment1.setDoctor(doctor);
        appointmentService.updateAppointmentInDB(appointment1);
        appointment1.setDoctor(doctor);
        appointment1.setId(2);
        appointmentService.saveAppointmentToDB(appointment1);
        appointmentService.deleteAppointmentInDB(appointment1.getId());
        Nurse nurse1 = appointmentService.getNurseInDB(1);

        //Checks if XML is valid
        StAXService stAXService = new StAXService();
        stAXService.validate(DOC_XML_FILE, DOC_XSD_FILE);
        stAXService.validate(NURSE_XML_FILE, NURSE_XSD_FILE);
        stAXService.validate(PATIENT_XML_FILE, PATIENT_XSD_FILE);

        //Parses XML File
        ArrayList<Doctor> temp =  stAXService.getDoctorFromXML(DOC_XML_FILE);
        temp.forEach(stAXService::insertDoctorFromXML);
        LOGGER.info("Doctor Id: " + temp.get(0).getId() + " Name: " + temp.get(0).getName()+ " " + temp.get(0).getPosition()
        + " DepartmentID :" + temp.get(0).getDepartment().getId() + " " + temp.get(0).getDepartment().getName());
        LOGGER.info("Doctor Id: " + temp.get(1).getId() + " Name: " + temp.get(1).getName()+ " " + temp.get(1).getPosition()
                + " DepartmentID :" + temp.get(1).getDepartment().getId() + " " + temp.get(1).getDepartment().getName());
        temp.get(0).setPosition("Neurologist");
        appointmentService.updateDoctorInDB(temp.get(0));

        ArrayList<Nurse> nurses =  stAXService.getNurseFromXML(NURSE_XML_FILE);
        nurses.forEach(stAXService::insertNurseFromXML);
        LOGGER.info("Nurse Id: " + nurses.get(0).getId() + " Name: " + nurses.get(0).getName()+ " " + nurses.get(0).getPosition()
                + " DepartmentID :" + nurses.get(0).getDepartment().getId() + " " + nurses.get(0).getDepartment().getName());
        LOGGER.info("Nurse Id: " + nurses.get(1).getId() + " Name: " + nurses.get(1).getName()+ " " + nurses.get(1).getPosition()
                + " DepartmentID :" + nurses.get(1).getDepartment().getId() + " " + nurses.get(1).getDepartment().getName());
        nurses.get(0).setPosition("Medical Assistant");
        appointmentService.updateNurseInDB(nurses.get(0));

        ArrayList<Patient> patients =  stAXService.getPatientFromXML(PATIENT_XML_FILE);
        patients.forEach(stAXService::insertPatientFromXML);
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
        stAXService.updateDoctorInXML(test, DOC_XML_FILE);

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
        tdataParsingService.marshal(treatmentData, TDATA_XML_FILE, TreatmentData.class.getName());


        //Unmarshaller with JAXB
        TreatmentData tdata = tdataParsingService.unmarshal(TDATA_XML_FILE, TreatmentData.class.getName());

        LOGGER.info("Unmarshaller test: " + tdata.getTreatment().getName() + " " + tdata.getTreatment().getCost()
           + tdata.getStartOfTreatment() + " " + tdata.getEndOfTreatment() + " " + tdata.getMedicalBill().getPatient().getName());

        //Serialize with Jackson
        JacksonService<TreatmentData> tdataJackson = new JacksonService<>();
        tdataJackson.serialize(tdata, TDATA_FILE);
        JacksonService<Doctor> doctorJacksonService = new JacksonService<>();
        JacksonService<Nurse> nurseJacksonService = new JacksonService<>();
        JacksonService<Patient> patientJacksonService = new JacksonService<>();
        JacksonService<Insurance> insuranceJacksonService = new JacksonService<>();
        doctorJacksonService.serialize(temp.get(0), DOC_FILE);
        nurseJacksonService.serialize(nurses.get(0), NURSE_FILE);
        patientJacksonService.serialize(patients.get(0), PATIENT_FILE);
        insuranceJacksonService.serialize(patients.get(0).getInsurance(), INSURANCE_FILE);

        //Deserialize with Jackson
        Insurance insurance = insuranceJacksonService.deserialize(INSURANCE_FILE, Insurance.class.getName());
        Doctor doc = doctorJacksonService.deserialize(DOC_FILE, Doctor.class.getName());
        TreatmentData tdata2 = tdataJackson.deserialize(TDATA_FILE,  TreatmentData.class.getName());
        Patient patient2 = patientJacksonService.deserialize(PATIENT_FILE, Patient.class.getName());
        Nurse nurse2 = nurseJacksonService.deserialize(NURSE_FILE, Nurse.class.getName());
        LOGGER.info("Deserializtion with Jackson on Insurance: " + insurance.getId() + " " +insurance.getName() );
        LOGGER.info("Deserializtion with Jackson on Doctor: " + doc.getId() + " " +doc.getName() );
        LOGGER.info("Deserializtion with Jackson on Patient: " + patient2.getId() + " " +patient2.getName() );
        LOGGER.info("Deserializtion with Jackson on Nurse: " + nurse2.getId() + " " +nurse2.getName() );
        LOGGER.info("Deserializtion with Jackson on TreatmentData: " + tdata2.getStartOfTreatment() + " " +tdata2.getEndOfTreatment() );

    }
}
