package com.organization.employeeManagement.repository;

import com.organization.employeeManagement.entities.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    void deleteAllByOrganizationId(int id);

    @Query(nativeQuery = true, name = "select count(*) from employees where department_id = :id")
    int countEmployeeById(@Param("id") Integer id);
}
