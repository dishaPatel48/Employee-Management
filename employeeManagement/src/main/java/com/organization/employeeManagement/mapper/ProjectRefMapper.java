package com.organization.employeeManagement.mapper;

import com.organization.employeeManagement.dto.ProjectRefDTO;
import com.organization.employeeManagement.entities.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectRefMapper {

    public static List<ProjectRefDTO> getProjectRefDTO(List<Project> projects) {
        List<ProjectRefDTO> projectRefDTOS = new ArrayList<>();
        projects.forEach(project -> {
            ProjectRefDTO p = new ProjectRefDTO();
            p.setId(project.getId());
            p.setName(project.getName());
            projectRefDTOS.add(p);
        });
        return projectRefDTOS;
    }

    public static ProjectRefDTO getProjectRefDTO(Project project) {
        ProjectRefDTO p = new ProjectRefDTO();
        p.setId(project.getId());
        p.setName(project.getName());
        return p;
    }
}
