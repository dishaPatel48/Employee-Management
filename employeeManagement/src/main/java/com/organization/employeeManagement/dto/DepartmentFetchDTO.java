package com.organization.employeeManagement.dto;

import java.util.List;

public class DepartmentFetchDTO {
    private Integer id;
    private String name;
    private List<EmployeeRefDTO> employees;
    private OrganizationRefDTO organization;

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

    public OrganizationRefDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationRefDTO organization) {
        this.organization = organization;
    }
}
