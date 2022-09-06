package com.ashish.app.model;

import org.springframework.stereotype.Component;

@Component
public class Patient {
    private String patient_name;
    private String patient_id;
    private String disease;
    private String hospital_name;

    public Patient(String patient_name,String patient_id, String disease, String hospital_name) {
        this.patient_name = patient_name;
        this.patient_id = patient_id;
        this.disease = disease;
        this.hospital_name = hospital_name;
    }

    public Patient() {
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }
}