package com.solvd.hospital.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name= "lab")
public class Lab {
    @XmlAttribute
    private int id;
    @XmlElement (name = "testName")
    private String testName;
    @XmlElement(name = "cost")
    private double cost;

    public Lab(){}
    public Lab(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
