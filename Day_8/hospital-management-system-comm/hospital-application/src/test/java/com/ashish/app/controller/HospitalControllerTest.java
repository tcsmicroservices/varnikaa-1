package com.ashish.app.controller;

import com.ashish.app.model.Hospital;
import com.ashish.app.model.Patient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.*;
import java.util.stream.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({HospitalController.class})
@ActiveProfiles(value = "test")
class HospitalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${hospital.get.url}")
    String hospitalGetUrl;
    @Value("${hospital.post.url}")
    String hospitalPostUrl;
    @Value("${hospital.put.url}")
    String hospitalPutUrl;
    @Value("${hospital.delete.url}")
    String hospitalDeleteUrl;

    @Test
    void getHospital() throws Exception{
        ResultActions responseEntity = mockMvc.perform(get(hospitalGetUrl).param("hospital_id","h1"));
        responseEntity.andExpect(status().isOk());
        ObjectMapper objectMapper = new ObjectMapper();
        Patient patient = new Patient("abhi","p1","fever","apex");
        List<Patient> patientList = new ArrayList<Patient>();
        patientList.add(patient);
        Hospital result = objectMapper.readValue(responseEntity.andReturn().getResponse().getContentAsString(),new TypeReference<Hospital>(){});
        assertEquals("h1",result.getHospital_id());
        assertEquals("apex",result.getHospital_name());
        assertEquals("sundarpur",result.getAddress());
        assertEquals("p1",result.getPatients().get(0).getPatient_id());
        List<Patient> check = result.getPatients().stream().collect(Collectors.toList());
        System.out.println(result.getPatients().stream().map(y -> y.toString()).collect(Collectors.toList()));
        System.out.println(check.getClass());
        System.out.println("patient "+patientList.getClass());
        //assertEquals(patientList,check);
        System.out.println(check);
        System.out.println(patientList);
        assertEquals(patientList,check);
        //assertEquals(patientList,result.getPatients().get(0));
    }

    @Test
    void saveHospital() throws Exception{
        Patient patient = new Patient("abhi","p1","fever","apex");
        List<Patient> patientList = new ArrayList<Patient>();
        patientList.add(patient);
        Hospital hospital = new Hospital("BHU","h3","lanka",patientList);

        ResultActions responseEntity = mockMvc.perform(post(hospitalPostUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(hospital)).accept(MediaType.APPLICATION_JSON));
        responseEntity.andExpect(status().isOk());
        ObjectMapper objectMapper = new ObjectMapper();
        Hospital result = objectMapper.readValue(responseEntity.andReturn().getResponse().getContentAsString(),new TypeReference<Hospital>(){});
        assertEquals("h3",result.getHospital_id());
        assertEquals("BHU",result.getHospital_name());
        assertEquals("lanka",result.getAddress());
        //assertEquals(patientList,result.getPatients());
        //assertEquals(patientList,result.getPatients());
    }
    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonContent = objectMapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateHospital() throws Exception{
        ResultActions responseEntity = mockMvc.perform(put(hospitalPutUrl).param("hospital_id","h4").param("address","cant"));
        responseEntity.andExpect(status().isOk());
        ObjectMapper objectMapper = new ObjectMapper();
        Hospital result = objectMapper.readValue(responseEntity.andReturn().getResponse().getContentAsString(), new TypeReference<Hospital>() {});
        assertEquals("h4",result.getHospital_id());
        assertEquals("apex",result.getHospital_name());
        assertEquals("cant",result.getAddress());
        //assertEquals(patientList,result.getPatients());
    }

    @Test
    void deleteHospital() throws Exception{
        ResultActions responseEntity  = mockMvc.perform(delete(hospitalDeleteUrl).param("hospital_id","h1"));
        responseEntity.andExpect(status().isOk());
        String result=responseEntity.andReturn().getResponse().getContentAsString();
        assertEquals("Deleted", result);
    }
}
