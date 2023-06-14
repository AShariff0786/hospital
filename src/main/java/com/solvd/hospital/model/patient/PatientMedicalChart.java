package com.solvd.hospital.model.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.hospital.util.CustomDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@JsonRootName(value = "patientMedicalChart")
@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement(name = "patientMedicalChart")
public class PatientMedicalChart {
    @JsonProperty(value = "id")
    @XmlAttribute
    private int id;
    @JsonProperty(value = "diagnosis")
    @XmlElement(name = "diagnosis")
    private String diagnosis;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
    @XmlJavaTypeAdapter(CustomDateAdapter.class)
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
