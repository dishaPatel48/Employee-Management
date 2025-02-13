package com.organization.employeeManagement.dto;

import java.util.List;

public class EmployeeFetchDTO {
    private Integer id;

    private String name;

    private List<DepartmentRefDTO> departments;
    private OrganizationRefDTO organization;
    private List<ProjectRefDTO> projects;

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

    public List<DepartmentRefDTO> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentRefDTO> departments) {
        this.departments = departments;
    }

    public OrganizationRefDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationRefDTO organization) {
        this.organization = organization;
    }

    public List<ProjectRefDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectRefDTO> projects) {
        this.projects = projects;
    }
}
