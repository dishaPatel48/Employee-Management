package com.organization.employeeManagement.repository;

import com.organization.employeeManagement.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findAllByOrganizationId(int orgId);

    void deleteAllByOrganizationId(int id);

    @Query("SELECT COUNT(e) FROM Employee e JOIN e.projects p WHERE p.id = :projectId")
    Integer countEmployeesByProjectId(@Param("projectId") Integer projectId);
}
