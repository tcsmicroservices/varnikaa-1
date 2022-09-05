package com.ashish.app.model;

import org.springframework.stereotype.Component;
import java.util.*;


@Component
public class Hospital {

    private String hospital_name;
    private String hospital_id;
    private String address;
    private List<Patient> patients;

    public Hospital() {
    }

    public Hospital(String hospital_name, String hospital_id,String address, List<Patient> patients) {
        this.hospital_name = hospital_name;
        this.hospital_id = hospital_id;
        this.address = address;
        this.patients = patients;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}