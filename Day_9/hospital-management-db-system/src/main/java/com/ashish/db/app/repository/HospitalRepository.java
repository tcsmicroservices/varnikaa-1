package com.ashish.db.app.repository;

import com.ashish.db.app.model.Hospital;
import org.springframework.data.repository.CrudRepository;

public interface HospitalRepository extends CrudRepository<Hospital,Integer> {
}