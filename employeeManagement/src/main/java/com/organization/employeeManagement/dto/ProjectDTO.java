package com.organization.employeeManagement.dto;

import java.util.List;

public class ProjectDTO {

    private String name;

    private List<EmployeeRefDTO> employees;

    private OrganizationRefDTO organization;

    public ProjectDTO() {
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
