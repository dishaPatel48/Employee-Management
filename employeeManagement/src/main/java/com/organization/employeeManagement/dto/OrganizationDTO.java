package com.organization.employeeManagement.dto;

public class OrganizationDTO {

    private String name;

    public OrganizationDTO() {
    }

    public OrganizationDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
