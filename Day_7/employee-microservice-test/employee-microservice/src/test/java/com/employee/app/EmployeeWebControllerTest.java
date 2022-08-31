package com.employee.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({EmployeeController.class})
@ActiveProfiles(value="test")
public class EmployeeWebControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Value("${employee.get.url}")
    String getUrl;
    @Value("${employee.post.url}")
    String postUrl;
    @Value("${employee.put.url}")
    String putUrl;
    @Value("${employee.delete.url}")
    String deleteUrl;

    @Test
    public void testEmployeeGet() throws Exception{
        ResultActions responseEntity = mockMvc.perform(get(getUrl).param("name","ashish"));
        responseEntity.andExpect(status().isOk());
        ObjectMapper objectMapper = new ObjectMapper();
        Employee result = objectMapper.readValue(responseEntity.andReturn().getResponse().getContentAsString(),new TypeReference<Employee>(){});
        assertEquals("test",result.getEmpName());
        assertEquals("123",result.getEmpId());
        assertEquals("Devop",result.getDepartment());
        assertEquals("300000",result.getSalary());
    }
    @Test
    public void testEmployeePost() throws Exception{
        Employee employee = new Employee("13","emp1","5000","AWS");
        ResultActions responseEntity = mockMvc.perform(post(postUrl).contentType(MediaType.APPLICATION_JSON).content(asJsonString(employee)).accept(MediaType.APPLICATION_JSON));
        responseEntity.andExpect(status().isOk());
        ObjectMapper objectMapper = new ObjectMapper();
        Employee result = objectMapper.readValue(responseEntity.andReturn().getResponse().getContentAsString(),new TypeReference<Employee>(){});
        assertEquals("emp1",result.getEmpName());
        assertEquals("13",result.getEmpId());
        assertEquals("AWS",result.getDepartment());
        assertEquals("5000",result.getSalary());
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
    public void testEmployeePut() throws Exception{
        ResultActions responseEntity = mockMvc.perform(put(putUrl).param("salary","300000").param("name","test"));
        responseEntity.andExpect(status().isOk());
        ObjectMapper objectMapper = new ObjectMapper();
        Employee result = objectMapper.readValue(responseEntity.andReturn().getResponse().getContentAsString(),new TypeReference<Employee>(){});
        assertEquals("test",result.getEmpName());
        assertEquals("123",result.getEmpId());
        assertEquals("Devop",result.getDepartment());
        assertEquals("300000",result.getSalary());
    }
    @Test
    public void testEmployeeDelete() throws Exception {
        ResultActions responseEntity  = mockMvc.perform(delete(deleteUrl).param("name","test"));
        responseEntity.andExpect(status().isOk());
        ObjectMapper mapper = new ObjectMapper();
        String result=responseEntity.andReturn().getResponse().getContentAsString();

        assertEquals("Deleted", result);

    }

}