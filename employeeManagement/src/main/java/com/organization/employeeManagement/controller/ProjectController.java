package com.organization.employeeManagement.controller;

import com.organization.employeeManagement.dto.EmployeeListUpdateDTO;
import com.organization.employeeManagement.dto.ProjectDTO;
import com.organization.employeeManagement.dto.ProjectFetchDTO;
import com.organization.employeeManagement.dto.ProjectUpdateDTO;
import com.organization.employeeManagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    @Lazy
    ProjectService projectService;

    @GetMapping(params = "orgId")
    public ResponseEntity<Map<String, Integer>> getProjects(@RequestParam int orgId) {
        Map<String, Integer> map = projectService.getEmployees(orgId);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectFetchDTO> getProject(@PathVariable int id) {
        ProjectFetchDTO project = projectService.getProject(id);
        return ResponseEntity.ok(project);
    }

    @PostMapping()
    public ResponseEntity<Void> addProject(@RequestBody ProjectDTO projectDTO) {
        projectService.addProject(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProject(@PathVariable("id") int id,
                                              @RequestBody ProjectUpdateDTO projectUpdateDTO) {
        projectService.updateProject(id, projectUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/employees")
    public ResponseEntity<Void> updateProjectEmployees(@PathVariable("id") int id,
                                                       @RequestBody EmployeeListUpdateDTO updateDTO) {
        projectService.updateProjectEmployees(id, updateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
