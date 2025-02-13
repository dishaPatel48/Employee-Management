package com.organization.employeeManagement.controller;

import com.organization.employeeManagement.dto.DepartmentRefDTO;
import com.organization.employeeManagement.dto.EmployeeDTO;
import com.organization.employeeManagement.dto.EmployeeDisplayDTO;
import com.organization.employeeManagement.dto.EmployeeFetchDTO;
import com.organization.employeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(params = "orgId")
    public ResponseEntity<List<EmployeeDisplayDTO>> getEmployees(@RequestParam Integer orgId) {
        List<EmployeeDisplayDTO> employees = employeeService.getEmployees(orgId);
        return ResponseEntity.ok(employees);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
//        Employee employee = employeeService.getEmployeeById(id);
//        return ResponseEntity.ok(employee);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeFetchDTO> getEmployeeById(@PathVariable Integer id) {
        EmployeeFetchDTO employee = employeeService.getEmployeeDTOById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping()
    public ResponseEntity<Void> addEmployees(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable Integer id) {
        employeeService.updateEmployee(employeeDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PatchMapping("/{id}/department")
    public ResponseEntity<Void> updateDepartment(@RequestBody DepartmentRefDTO dto, @PathVariable Integer id) {
        employeeService.updateDepartment(dto, id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(null);
    }

}
