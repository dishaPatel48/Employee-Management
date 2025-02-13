package com.organization.employeeManagement.service;

import com.organization.employeeManagement.dto.*;
import com.organization.employeeManagement.entities.Department;
import com.organization.employeeManagement.entities.Employee;
import com.organization.employeeManagement.entities.Organization;
import com.organization.employeeManagement.entities.Project;
import com.organization.employeeManagement.exception.IncompleteInformation;
import com.organization.employeeManagement.exception.RecordDoesNotExist;
import com.organization.employeeManagement.mapper.DepartmentRefMapper;
import com.organization.employeeManagement.mapper.OrganizationRefMapper;
import com.organization.employeeManagement.mapper.ProjectRefMapper;
import com.organization.employeeManagement.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    @Lazy
    private OrganizationService organizationService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    @Lazy
    ProjectService projectService;


    public List<EmployeeDisplayDTO> getEmployees(int orgId) {
        if (organizationService.getOrganization(orgId) == null) {
            throw new RecordDoesNotExist("Organization does not exist with id: " + orgId);
        }
        List<Employee> employees = employeeRepository.findAllByOrganizationId(orgId);
        List<EmployeeDisplayDTO> employeeDisplayDTOS = new ArrayList<>();
        employees.stream().forEach(employee -> {
            EmployeeDisplayDTO dto = new EmployeeDisplayDTO();
            dto.setId(employee.getId());
            dto.setName(employee.getName());
            employeeDisplayDTOS.add(dto);
        });
        return employeeDisplayDTOS;
    }

    public Employee getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RecordDoesNotExist("Employee does not" + " exist " + "with id: " + id));
        return employee;
    }

    public EmployeeFetchDTO getEmployeeDTOById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RecordDoesNotExist("Employee does not" + " exist " + "with id: " + id));

        EmployeeFetchDTO dto = new EmployeeFetchDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        // department
        dto.setDepartments(DepartmentRefMapper.getDepRefDTO(employee.getDepartments()));
        // projects
        dto.setProjects(ProjectRefMapper.getProjectRefDTO(employee.getProjects()));
        // organization
        dto.setOrganization(OrganizationRefMapper.getOrgRefDTO(employee.getOrganization()));
        return dto;
    }


    @Transactional
    public void addEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO.getName() == null || employeeDTO.getDepartments() == null || employeeDTO.getOrganization() == null) {
            throw new IncompleteInformation("Enter All the fields");
        }
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());

        // department
        List<Department> departments = departmentService.getDepartmentsByIds(employeeDTO.getDepartments());
        employee.setDepartments(departments);

        // organization
        Organization organization = organizationService.getOrganizationById(employeeDTO.getOrganization().getId());
        employee.setOrganization(organization);

        // projects
        if (employeeDTO.getProjects() != null && !employeeDTO.getProjects().isEmpty()) {
            List<Project> projects = projectService.getProjectsByIds(employeeDTO.getProjects());
            employee.setProjects(projects);
        }

        employeeRepository.save(employee);

    }

    public void updateEmployee(EmployeeDTO employeeDTO, int id) {
        Employee employee = getEmployeeById(id);

        if (employeeDTO.getName() == null) {
            throw new IncompleteInformation("Enter Name");
        }

        employee.setName(employeeDTO.getName());

        employeeRepository.save(employee);
    }

    public void updateDepartment(DepartmentRefDTO dto, Integer id) {
        Employee employee = getEmployeeById(id);
        Department department = departmentService.getDepartmentById(dto.getId());
        employee.getDepartments().add(department);
        employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RecordDoesNotExist("Employee does not" + " exist " + "with id: " + id));

        employeeRepository.delete(employee);
    }

    public void deleteEmployeeByOrganizationId(int id) {
        employeeRepository.deleteAllByOrganizationId(id);
    }


}

