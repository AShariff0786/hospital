package com.solvd.hospital.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@JsonRootName(value = "treatment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name = "treatment")
public class Treatment {
    @JsonProperty(value = "id")
    @XmlAttribute
    private int id;
    @JsonProperty(value = "name")
    @XmlElement (name = "name")
    private String name;
    @JsonProperty(value = "cost")
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
