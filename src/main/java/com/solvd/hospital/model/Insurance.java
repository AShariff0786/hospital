package com.solvd.hospital.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name = "insurance")
public class Insurance {
    @XmlAttribute
    private int id;
    @XmlElement (name = "name")
    private String name;

    public Insurance(){}
    public Insurance (int id){
        this. id = id;
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
}
