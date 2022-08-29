package com.employee.app;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
public class EmployeeController {

    private Map<String,Employee> employeeMap = new HashMap<String,Employee>();

    @GetMapping("/get/employee")
    public Employee getEmployee(@RequestParam String name){
        Employee result= employeeMap.get(name);
        return result;
    }

    @PostMapping("/save/employee")
    public void SaveEmployee(@RequestBody Employee employee){
        String employeeName = employee.getEmpName();
        employeeMap.put(employeeName,employee);
    }

    @PutMapping("/update/employee")
    public Employee updateEmployee(@RequestParam String salary,@RequestParam String name){
        Employee result= employeeMap.get(name);
        result.setSalary(salary);
        employeeMap.put(name,result);
        return result;

    }
    @DeleteMapping("/remove/employee")
    public void deleteEmployee(@RequestParam String name){
        employeeMap.remove(name);
    }

}
