package com.solvd.hospital.model.patient;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement(name = "patientMedicalChart")
public class PatientMedicalChart {
    @XmlAttribute
    private int id;
    @XmlElement(name = "diagnosis")
    private String diagnosis;

    private Date date;
    public PatientMedicalChart(){}
    public PatientMedicalChart (int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
