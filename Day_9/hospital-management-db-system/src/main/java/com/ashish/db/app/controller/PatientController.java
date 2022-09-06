package com.ashish.db.app.controller;

import com.ashish.db.app.model.Patient;
import com.ashish.db.app.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @PostMapping("/save")
    @ResponseBody public String savePatient(@RequestBody Patient patient){
        patientRepository.save(patient);
        return "saved";
    }
    @GetMapping("/get")
    @ResponseBody public Iterable<Patient> getPatients(){
        return patientRepository.findAll();
    }
    @DeleteMapping("/delete")
    @ResponseBody public String deletePatientById(@RequestParam Integer id){
        patientRepository.deleteById(id);
        return "Deleted";
    }
    @PutMapping("/update")
    @ResponseBody public Patient updatePatient(@RequestParam Integer id,@RequestParam String disease){
        Patient patient = patientRepository.findById(id).get();
        patient.setDisease(disease);
        patientRepository.save(patient);
        return patient;
    }
}
