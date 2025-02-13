package com.organization.employeeManagement.service;

import com.organization.employeeManagement.dto.EmployeeListUpdateDTO;
import com.organization.employeeManagement.dto.ProjectDTO;
import com.organization.employeeManagement.dto.ProjectFetchDTO;
import com.organization.employeeManagement.dto.ProjectUpdateDTO;
import com.organization.employeeManagement.entities.Employee;
import com.organization.employeeManagement.entities.Organization;
import com.organization.employeeManagement.entities.Project;
import com.organization.employeeManagement.exception.IncompleteInformation;
import com.organization.employeeManagement.exception.RecordDoesNotExist;
import com.organization.employeeManagement.mapper.EmployeeRefMapper;
import com.organization.employeeManagement.mapper.OrganizationRefMapper;
import com.organization.employeeManagement.repository.EmployeeRepository;
import com.organization.employeeManagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    OrganizationService organizationService;


    public Map<String, Integer> getEmployees(int orgId) {
        Map<String, Integer> map = new HashMap<>();
        List<Project> projects = projectRepository.findAllByOrganizationId(orgId);

        projects.stream().forEach(project -> {
            Integer count = employeeRepository.countEmployeesByProjectId(project.getId());
            map.put(project.getName(), count);
        });

        return map;
    }

    public ProjectFetchDTO getProject(int id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RecordDoesNotExist("Project does not " +
                "exist with id: " + id));
        ProjectFetchDTO dto = new ProjectFetchDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setEmployees(EmployeeRefMapper.getEmpRefDTO(project.getEmployees()));
        dto.setOrganization(OrganizationRefMapper.getOrgRefDTO(project.getOrganization()));
        return dto;
    }

    public List<Project> getProjectsByIds(List<Integer> projectIds) {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAllById(projectIds).forEach(projects::add);
        return projects;
    }

    public void addProject(ProjectDTO projectDTO) {
        if (projectDTO.getName() == null || projectDTO.getOrganization() == null) {
            throw new IncompleteInformation("Enter all fields");
        }
        Project project = new Project();
        project.setName(projectDTO.getName());

        if (projectDTO.getEmployees() != null) {
            List<Integer> ids = projectDTO.getEmployees().stream().map(p -> p.getId()).collect(Collectors.toList());
            List<Employee> employees = new ArrayList<>();
            employeeRepository.findAllById(ids).forEach(employees::add);
            project.setEmployees(employees);
        }
        Organization organization = organizationService.getOrganizationById(projectDTO.getOrganization().getId());
        project.setOrganization(organization);
        projectRepository.save(project);
    }

    public void updateProject(int id, ProjectUpdateDTO projectUpdateDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RecordDoesNotExist("project does not exist with id: " + id));
        project.setName(projectUpdateDTO.getName());
        projectRepository.save(project);
    }


    public void updateProjectEmployees(int id, EmployeeListUpdateDTO updateDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RecordDoesNotExist("project does not exist with id: " + id));

        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAllById(updateDTO.getIds()).forEach(employee ->
                {
                    if (employee.getProjects().contains(project)) {
                        employee.getProjects().remove(project);
                    } else {
                        employee.getProjects().add(project);
                    }
                    employeeRepository.save(employee);
                }
        );

        project.setEmployees(employees);
        projectRepository.save(project);
    }

    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }
}
