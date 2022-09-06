package com.ashish.app.controller;


import java.util.*;
import com.ashish.app.model.Hospital;
import com.ashish.app.model.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@RestController
public class HospitalController {

    @Autowired
    private RestTemplate restTemplate;


    private HashMap<String,Hospital> hospitalhmap = new HashMap<>();
    private HashMap<String, Patient> patienthmap = new HashMap<>();

    @GetMapping("/hospital/get")
    public Hospital getHospital(@RequestParam String hospital_id){
        Hospital hospital = hospitalhmap.get(hospital_id);
        if(hospital == null){
            ResponseEntity<Patient> patient = restTemplate.exchange("http://localhost:8082/patient/get/?patient_id=p1", HttpMethod.GET,null,Patient.class);
            //Patient patient = new Patient("abhi","p1","fever","apex");
            List<Patient> patientList = new ArrayList<Patient>();
            patientList.add(patient.getBody());
            Hospital test = new Hospital("apex", "h1", "sundarpur", patientList);
            return test;

        }else{
            return hospital;
        }

    }
    @PostMapping("/hospital/save")
    public Hospital saveHospital(@RequestBody Hospital hospital){
        String hospitalId = hospital.getHospital_id();
        HttpHeaders header = new HttpHeaders();
        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //HttpEntity<Patient> res = new HttpEntity(hospital.getPatients(),header);
        ResponseEntity<Patient> patient = restTemplate.exchange("http://localhost:8082/patient/save", HttpMethod.POST,null,Patient.class);
        hospital.getPatients();
        hospitalhmap.put(hospitalId,hospital);
        return hospital;
    }
    @PutMapping("/hospital/update")
    public Hospital updateHospital(@RequestParam String hospital_id,@RequestParam String address){
        Hospital hospital = hospitalhmap.get(hospital_id);
        if(hospital == null){
            ResponseEntity<Patient> patient = restTemplate.exchange("http://localhost:8082/patient/update", HttpMethod.PUT,null,Patient.class);
            //Patient patient = new Patient("abhi","p1","fever","apex");
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
    public String deleteHospital(@RequestParam String hospital_id) {
        hospitalhmap.remove(hospital_id);
        HttpHeaders header = new HttpHeaders();
        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> res = new HttpEntity<String>(header);
        String result = restTemplate.exchange("http://localhost:8082/patient/delete?patient_id=p1",HttpMethod.DELETE,res,String.class).getBody();
        return "Deleted Hospital "+ result;
    }
}