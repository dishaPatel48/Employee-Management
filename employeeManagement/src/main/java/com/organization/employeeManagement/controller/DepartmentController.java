package com.organization.employeeManagement.controller;

import com.organization.employeeManagement.dto.*;
import com.organization.employeeManagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Secured("ROLE_MANAGER")
    @GetMapping()
    public ResponseEntity<List<DepartmentDisplayDTO>> getDepartments() {
        List<DepartmentDisplayDTO> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

    @Secured("ROLE_MANAGER")
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentFetchDTO> getDepartment(@PathVariable int id) {
        DepartmentFetchDTO department = departmentService.getDepartment(id);
        return ResponseEntity.ok(department);
    }

    @Secured("ROLE_FOUNDER")
    @PostMapping()
    public ResponseEntity<Void> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.addDepartment(departmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @Secured("ROLE_FOUNDER")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@RequestBody DepartmentUpdateDTO departmentUpdateDTO,
                                                 @PathVariable int id) {
        departmentService.updateDepartment(departmentUpdateDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Secured({"ROLE_FOUNDER", "ROLE_MANAGER"})
    @PatchMapping("/{id}/employees")
    public ResponseEntity<Void> updateProjectEmployees(@PathVariable("id") int id,
                                                       @RequestBody EmployeeListUpdateDTO updateDTO) {
        departmentService.updateDepartmentEmployees(id, updateDTO);
        return ResponseEntity.ok().build();
    }

    @Secured("ROLE_FOUNDER")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
