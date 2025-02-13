package com.organization.employeeManagement.mapper;

import com.organization.employeeManagement.dto.DepartmentRefDTO;
import com.organization.employeeManagement.entities.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentRefMapper {

    public static List<DepartmentRefDTO> getDepRefDTO(List<Department> departments) {
        List<DepartmentRefDTO> departmentRefDTOS = new ArrayList<>();
        departments.forEach(department -> {
            DepartmentRefDTO d = new DepartmentRefDTO();
            d.setId(department.getId());
            d.setName(department.getName());
            departmentRefDTOS.add(d);
        });

        return departmentRefDTOS;
    }

    public static DepartmentRefDTO getDepRefDTO(Department department) {
        DepartmentRefDTO d = new DepartmentRefDTO();
        d.setId(department.getId());
        d.setName(department.getName());
        return d;
    }
}
