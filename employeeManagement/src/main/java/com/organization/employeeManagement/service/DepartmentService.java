package com.organization.employeeManagement.service;

import com.organization.employeeManagement.dto.*;
import com.organization.employeeManagement.entities.Department;
import com.organization.employeeManagement.entities.Employee;
import com.organization.employeeManagement.entities.Organization;
import com.organization.employeeManagement.exception.IncompleteInformation;
import com.organization.employeeManagement.exception.RecordDoesNotExist;
import com.organization.employeeManagement.mapper.EmployeeRefMapper;
import com.organization.employeeManagement.mapper.OrganizationRefMapper;
import com.organization.employeeManagement.repository.DepartmentRepository;
import com.organization.employeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    private OrganizationService organizationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    public DepartmentService(@Lazy OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    public void deleteDepartmentByOrganizationId(int id) {
        departmentRepository.deleteAllByOrganizationId(id);
    }

    public List<DepartmentDisplayDTO> getDepartments() {
        List<Department> departments = new ArrayList<>();
        List<DepartmentDisplayDTO> dtos = new ArrayList<>();
        departmentRepository.findAll().forEach(department -> {
            DepartmentDisplayDTO dto = new DepartmentDisplayDTO();
            dto.setId(department.getId());
            dto.setName(department.getName());
            dto.setEmployeeCount(departmentRepository.countEmployeeById(department.getId()));
            dtos.add(dto);

        });
        return dtos;
    }

    public Department getDepartmentById(int id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RecordDoesNotExist("Department does not" +
                        " exist " +
                        "with id: " + id));
//        List<>
//        department.getEmployees().forEach();

        return department;
    }

    public DepartmentFetchDTO getDepartment(int id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RecordDoesNotExist("Department does not" +
                        " exist " +
                        "with id: " + id));
        DepartmentFetchDTO dto = new DepartmentFetchDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setEmployees(EmployeeRefMapper.getEmpRefDTO(department.getEmployees()));
        dto.setOrganization(OrganizationRefMapper.getOrgRefDTO(department.getOrganization()));
        return dto;
    }

    public List<Department> getDepartmentsByIds(List<Integer> ids) {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAllById(ids).forEach(departments::add);
        return departments;
    }

    public void addDepartment(DepartmentDTO departmentDTO) {
        if (departmentDTO.getName().isBlank()) {
            throw new IncompleteInformation("Enter All the fields");
        }
        String orgName = departmentDTO.getOrganization().getName();
        Organization organization = organizationService.getOrganizationByName(orgName);
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setOrganization(organization);
        departmentRepository.save(department);
    }

    public void updateDepartment(DepartmentUpdateDTO departmentUpdateDTO, int id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RecordDoesNotExist(
                "Department does not" +
                        " exist " +
                        "with id: " + id));
        department.setName(departmentUpdateDTO.getName());
        departmentRepository.save(department);
    }


    public void deleteDepartment(int id) {
        if (!departmentRepository.existsById(id)) {
            throw new RecordDoesNotExist("Department does not" +
                    " exist " +
                    "with id: " + id);
        }
        departmentRepository.deleteById(id);
    }

    public void updateDepartmentEmployees(int id, EmployeeListUpdateDTO updateDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RecordDoesNotExist("project does not exist with id: " + id));

        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAllById(updateDTO.getIds()).forEach(employee ->
                {
                    if (employee.getDepartments().contains(department)) {
                        employee.getDepartments().remove(department);
                    } else {
                        employee.getDepartments().add(department);
                    }
                    employeeRepository.save(employee);
                }
        );

        department.setEmployees(employees);
        departmentRepository.save(department);
    }
}
