package com.organization.employeeManagement.dto;

import com.organization.employeeManagement.entities.Organization;
public class DepartmentDTO {
    private String name;
    private OrganizationDTO organization;
    public DepartmentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }
}
