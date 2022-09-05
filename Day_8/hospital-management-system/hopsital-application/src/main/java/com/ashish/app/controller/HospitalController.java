package com.ashish.app.controller;


import java.util.*;
import com.ashish.app.model.Hospital;
import com.ashish.app.model.Patient;

import com.ashish.app.model.Hospital;
import com.ashish.app.model.Patient;
import org.springframework.web.bind.annotation.*;


@RestController
public class HospitalController {

    private HashMap<String, Hospital> hospitalhmap = new HashMap<>();
    private HashMap<String, Patient> patienthmap = new HashMap<>();

    @GetMapping("/hospital/get")
    public Hospital getHospital(@RequestParam String hospital_id){
        Hospital hospital = hospitalhmap.get(hospital_id);
        if(hospital == null){
            Patient patient = new Patient("abhi","p1","fever","apex");
            List<Patient> patientList = new ArrayList<Patient>();
            patientList.add(patient);
            Hospital test = new Hospital("apex", "h1", "sundarpur", patientList);
            return test;
        }else{
            return hospital;
        }

    }
    @PostMapping("/hospital/save")
    public Hospital saveHospital(@RequestBody Hospital hospital){
        String hospitalId = hospital.getHospital_id();
        hospitalhmap.put(hospitalId,hospital);
        return hospital;
    }
    @PutMapping("/hospital/update")
    public Hospital updateHospital(@RequestParam String hospital_id,@RequestParam String address){
        Hospital hospital = hospitalhmap.get(hospital_id);
        if(hospital == null){
            Patient patient = new Patient("abhi","p1","fever","apex");
            List<Patient> patientList = new ArrayList<Patient>();
            patientList.add(patient);
            Hospital test = new Hospital("apex", hospital_id, address, patientList);
            hospitalhmap.put(hospital_id,test);
            return test;
        }else{
            hospital.setAddress(address);
            hospitalhmap.put(hospital_id,hospital);
            return hospital;
        }

    }

    @DeleteMapping("/hospital/delete")
    public String deleteHospital(@RequestParam String hospital_id){
        hospitalhmap.remove(hospital_id);
        return "Deleted";
    }
}