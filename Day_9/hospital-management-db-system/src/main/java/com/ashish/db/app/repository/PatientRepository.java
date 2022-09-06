package com.ashish.db.app.repository;

import com.ashish.db.app.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient,Integer> {
}
