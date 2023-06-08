package com.solvd.hospital.model;

public class Medication {
    private int id;
    private String name;
    private String brandName;

    public Medication(){}
    public Medication (int id, String name){
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
