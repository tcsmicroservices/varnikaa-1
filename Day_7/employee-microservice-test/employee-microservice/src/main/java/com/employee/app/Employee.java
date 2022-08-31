package com.employee.app;

import org.springframework.stereotype.Component;

@Component
public class Employee {
    String empId;
    String empName;
    String salary;
    String department;

    public Employee(String empId, String empName , String salary, String department){
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.department = department;
    }
    public Employee(){

    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }





}


