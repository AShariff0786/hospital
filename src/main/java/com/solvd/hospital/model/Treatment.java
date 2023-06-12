package com.solvd.hospital.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name = "treatment")
public class Treatment {
    @XmlAttribute
    private int id;
    @XmlElement (name = "name")
    private String name;
    @XmlElement (name = "cost")
    private double cost;

    public Treatment(){}
    public Treatment(int id, String name){
        this.id = id;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
