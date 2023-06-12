package com.solvd.hospital.model.patient;

import com.solvd.hospital.model.Treatment;
import com.solvd.hospital.util.CustomDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement (name = "treatmentData")
public class TreatmentData {
    private MedicalBill medicalBill;
    private Treatment treatment;

    private Date startOfTreatment;

    private Date endOfTreatment;

    public TreatmentData(){}
    public TreatmentData(MedicalBill medicalBill, Treatment treatment){
        this.medicalBill = medicalBill;
        this.treatment = treatment;
    }

    @XmlElement(name = "medicalBill")
    public MedicalBill getMedicalBill() {
        return medicalBill;
    }

    public void setMedicalBill(MedicalBill medicalBill) {
        this.medicalBill = medicalBill;
    }

    @XmlElement (name = "treatment")
    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    @XmlJavaTypeAdapter(CustomDateAdapter.class)
    public Date getStartOfTreatment() {
        return startOfTreatment;
    }

    public void setStartOfTreatment(Date startOfTreatment) {
        this.startOfTreatment = startOfTreatment;
    }

    @XmlJavaTypeAdapter(CustomDateAdapter.class)
    public Date getEndOfTreatment() {
        return endOfTreatment;
    }

    public void setEndOfTreatment(Date endOfTreatment) {
        this.endOfTreatment = endOfTreatment;
    }
}
