package com.organization.employeeManagement.dto;

import com.organization.employeeManagement.entities.Department;
import com.organization.employeeManagement.entities.Employee;

import java.util.List;

public class OrganizationFetchDTO {
    private Integer id;
    private String name;
    private List<EmployeeRefDTO> employees;
    private List<DepartmentRefDTO> departments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeRefDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeRefDTO> employees) {
        this.employees = employees;
    }

    public List<DepartmentRefDTO> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentRefDTO> departments) {
        this.departments = departments;
    }
}
