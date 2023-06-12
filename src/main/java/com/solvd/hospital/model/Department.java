package com.solvd.hospital.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name = "department")
public class Department {
    @XmlAttribute
    private int id;
    @XmlElement(name = "name")
    private String name;
    @XmlTransient
    private List<Doctor> doctors;
    @XmlTransient
    private List<Nurse> nurses;

    public Department(){}
    public Department (int id, String name){
        this. id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }
}
