package com.solvd.hospital.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public abstract class User {

    private int id;
    private String name;

    public User(){}

    public User (int id){
        this.id =id;
    }
    public User (int id, String name){
        this.id = id;
        this.name = name;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement (name ="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
