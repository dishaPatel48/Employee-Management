package com.organization.employeeManagement.service;

import com.organization.employeeManagement.dto.OrganizationDTO;
import com.organization.employeeManagement.dto.OrganizationObjDTO;
import com.organization.employeeManagement.dto.OrganizationFetchDTO;
import com.organization.employeeManagement.entities.Organization;
import com.organization.employeeManagement.exception.IncompleteInformation;
import com.organization.employeeManagement.exception.RecordAlreadyExist;
import com.organization.employeeManagement.exception.RecordDoesNotExist;
import com.organization.employeeManagement.mapper.DepartmentRefMapper;
import com.organization.employeeManagement.mapper.EmployeeRefMapper;
import com.organization.employeeManagement.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    public List<OrganizationObjDTO> getOrganizations() {
        List<OrganizationObjDTO> result = new ArrayList<>();
        List<Organization> organizations = new ArrayList<>();
        organizationRepository.findAll().forEach(organization -> {
            OrganizationObjDTO org = new OrganizationObjDTO();
            org.setId(organization.getId());
            org.setName(organization.getName());
            org.setEmployeeCount(organizationRepository.countEmployeeById(organization.getId()));
            org.setDepartmentCount(organizationRepository.countDepartmentById(organization.getId()));
            org.setProjectCount(organizationRepository.countProjectById(organization.getId()));
            result.add(org);
        });
        return result;
    }

    public OrganizationFetchDTO getOrganization(int id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new RecordDoesNotExist("Organization does not" +
                        " exist " +
                        "with id: " + id));

        OrganizationFetchDTO objDTO = new OrganizationFetchDTO();
        objDTO.setId(organization.getId());
        objDTO.setName(organization.getName());
        objDTO.setDepartments(DepartmentRefMapper.getDepRefDTO(organization.getDepartments()));
        objDTO.setEmployees(EmployeeRefMapper.getEmpRefDTO(organization.getEmployees()));
        return objDTO;
    }

    public Organization getOrganizationByName(String name) {
        Organization organization = organizationRepository.findByName(name);
        if (organization == null) {
            throw new RecordDoesNotExist("Organization does not exist with name: " + name);
        }
        return organization;
    }


    public void addOrganization(OrganizationDTO dto) {
        if (dto.getName().isBlank()) {
            throw new IncompleteInformation("Enter All the fields");
        }
        if (organizationRepository.existsByName(dto.getName())) {
            throw new RecordAlreadyExist("Organization already exists with name: " + dto.getName());
        }
        Organization organization = new Organization();
        organization.setName(dto.getName());
        organizationRepository.save(organization);
    }

    public void updateOrganization(OrganizationDTO dto, Integer id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new RecordDoesNotExist(
                "Organization does not exist with id:  " + id));

        organization.setName(dto.getName());
        organizationRepository.save(organization);
    }

    public void deleteOrganization(Integer id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new RecordDoesNotExist(
                "Organization does not exist with id:  " + id));

        employeeService.deleteEmployeeByOrganizationId(id);
        departmentService.deleteDepartmentByOrganizationId(id);
        organizationRepository.delete(organization);
    }

    public Organization getOrganizationById(int id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new RecordDoesNotExist("Organization does not" +
                        " exist " +
                        "with id: " + id));
    }
}
