package com.solvd.hospital.model;

import com.fasterxml.jackson.annotation.*;

import javax.xml.bind.annotation.*;


@JsonRootName(value = "insurance")
@JsonIncludeProperties({"id", "name"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement (name = "insurance")
public class Insurance {
    @JsonProperty(value = "id")
    @XmlAttribute
    private int id;
    @JsonProperty(value = "name")
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
