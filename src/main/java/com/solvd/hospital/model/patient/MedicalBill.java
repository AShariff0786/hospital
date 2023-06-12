package com.solvd.hospital.model.patient;

import com.solvd.hospital.model.Insurance;
import com.solvd.hospital.model.Lab;
import com.solvd.hospital.model.Treatment;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "medicalBill")
@XmlAccessorType(XmlAccessType.FIELD)
public class MedicalBill {
    @XmlAttribute (name = "id" )
    private int id;
    @XmlElement (name = "amountDue")
    private double amountDue;
    @XmlElement (name = "patient")
    private Patient patient;
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
