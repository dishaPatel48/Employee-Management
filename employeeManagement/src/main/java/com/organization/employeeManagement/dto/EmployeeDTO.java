package com.organization.employeeManagement.dto;

import com.organization.employeeManagement.entities.Organization;
import com.organization.employeeManagement.entities.Project;

import java.util.List;

public class EmployeeDTO {

    private String name;

    private List<Integer> departments;

    private OrganizationRefDTO organization;

    private List<Integer> projects;

    public EmployeeDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationRefDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationRefDTO organization) {
        this.organization = organization;
    }

    public List<Integer> getProjects() {
        return projects;
    }

    public void setProjects(List<Integer> projects) {
        this.projects = projects;
    }

    public List<Integer> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Integer> departments) {
        this.departments = departments;
    }
}
