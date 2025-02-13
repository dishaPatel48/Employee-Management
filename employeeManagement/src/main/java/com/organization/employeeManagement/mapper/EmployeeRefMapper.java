package com.organization.employeeManagement.mapper;

import com.organization.employeeManagement.dto.EmployeeRefDTO;
import com.organization.employeeManagement.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRefMapper {

    public static List<EmployeeRefDTO> getEmpRefDTO(List<Employee> employeeList) {
        List<EmployeeRefDTO> employees = new ArrayList<>();
        employeeList.forEach(employee -> {
            EmployeeRefDTO e = new EmployeeRefDTO();
            e.setId(employee.getId());
            e.setName(employee.getName());
            employees.add(e);
        });
        return employees;
    }

    public static EmployeeRefDTO getEmpRefDTO(Employee employee) {
        EmployeeRefDTO e = new EmployeeRefDTO();
        e.setId(employee.getId());
        e.setName(employee.getName());
        return e;
    }


}
