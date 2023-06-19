package com.solvd.hospital.model.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.hospital.model.Insurance;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@JsonRootName(value = "medicalBill")
@XmlRootElement(name = "medicalBill")
@XmlAccessorType(XmlAccessType.FIELD)
public class MedicalBill {
    @JsonProperty(value = "id")
    @XmlAttribute(name = "id" )
    private int id;
    @JsonProperty(value = "amountDue")
    @XmlElement(name = "amountDue")
    private double amountDue;
    @JsonProperty(value = "patient")
    @XmlElement (name = "patient")
    private Patient patient;
    @JsonProperty(value = "insurance")
    @XmlElement (name = "insurance")
    private Insurance insurance;


    public MedicalBill(){}
    public MedicalBill(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

}
