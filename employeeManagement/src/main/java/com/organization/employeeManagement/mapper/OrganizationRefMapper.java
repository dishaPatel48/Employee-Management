package com.organization.employeeManagement.mapper;

import com.organization.employeeManagement.dto.OrganizationRefDTO;
import com.organization.employeeManagement.entities.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationRefMapper {

    public static OrganizationRefDTO getOrgRefDTO(Organization organization) {
        OrganizationRefDTO orgDTO = new OrganizationRefDTO();
        orgDTO.setId(organization.getId());
        orgDTO.setName(organization.getName());
        return orgDTO;
    }

    public static List<OrganizationRefDTO> getOrgRefDTO(List<Organization> organizations) {
        List<OrganizationRefDTO> dtos = new ArrayList<>();
        organizations.forEach(organization -> {
            OrganizationRefDTO orgDTO = new OrganizationRefDTO();
            orgDTO.setId(organization.getId());
            orgDTO.setName(organization.getName());
            dtos.add(orgDTO);
        });
        return dtos;
    }
}
