package com.solvd.hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@JsonRootName(value = "department")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name = "department")
public class Department {
    @JsonProperty(value = "id")
    @XmlAttribute
    private int id;
    @JsonProperty(value = "name")
    @XmlElement(name = "name")
    private String name;
    @JsonIgnore
    @XmlTransient
    private List<Doctor> doctors;
    @JsonIgnore
    @XmlTransient
    private List<Nurse> nurses;

    public Department(){}
    public Department(int id){
        this.id = id;
    }
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
