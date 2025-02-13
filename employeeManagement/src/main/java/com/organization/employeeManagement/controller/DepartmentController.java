package com.organization.employeeManagement.controller;

import com.organization.employeeManagement.dto.*;
import com.organization.employeeManagement.entities.Department;
import com.organization.employeeManagement.entities.Employee;
import com.organization.employeeManagement.exception.RecordDoesNotExist;
import com.organization.employeeManagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<List<DepartmentDisplayDTO>> getDepartments() {
        List<DepartmentDisplayDTO> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentFetchDTO> getDepartment(@PathVariable int id) {
        DepartmentFetchDTO department = departmentService.getDepartment(id);
        return ResponseEntity.ok(department);
    }

    @PostMapping()
    public ResponseEntity<Void> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.addDepartment(departmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@RequestBody DepartmentUpdateDTO departmentUpdateDTO,
                                                 @PathVariable int id) {
        departmentService.updateDepartment(departmentUpdateDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping("/{id}/employees")
    public ResponseEntity<Void> updateProjectEmployees(@PathVariable("id") int id,
                                                       @RequestBody EmployeeListUpdateDTO updateDTO) {
        departmentService.updateDepartmentEmployees(id, updateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
