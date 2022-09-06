package com.ashish.app.controller;

import java.util.*;

import com.ashish.app.model.Patient;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {
    private HashMap<String, Patient> patienthmap = new HashMap<>();

    @GetMapping("/patient/get")
    public Patient getPatient(@RequestParam String patient_id){
        Patient patient = patienthmap.get(patient_id);
        if(patient == null){
            Patient test = new Patient("abhi","p1","fever","apex");
            return test;
        }else{
            return patient;
        }
    }
    @DeleteMapping("/patient/delete")
    public String deletePatient(@RequestParam String patient_id){
        patienthmap.remove(patient_id);
        return "Deleted";

    }
    @PostMapping("/patient/save")
    public Patient savePatient(@RequestBody Patient patient){
        String patientId = patient.getPatient_id();
        patienthmap.put(patientId,patient);
        return patient;
    }
    @PutMapping("/patient/update")
    public Patient updatePatient(@RequestParam String patient_id,@RequestParam String disease){
        Patient patient = patienthmap.get(patient_id);
        if(patient == null){
            Patient test = new Patient("abhi","p1","fever","apex");
            patienthmap.put(patient_id,test);
            return test;
        }else{
            patient.setDisease(disease);
            patienthmap.put(patient_id,patient);
            return patient;
        }

    }
}