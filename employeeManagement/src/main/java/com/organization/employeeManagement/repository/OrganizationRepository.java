package com.organization.employeeManagement.repository;

import com.organization.employeeManagement.entities.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
    Organization findByName(String name);

    boolean existsByName(String name);

    @Query(nativeQuery = true, name = "select count(*) from employees where organization_id = :id")
    int countEmployeeById(@Param("id") Integer id);

    @Query(nativeQuery = true, name = "select count(*) from departments where organization_id = :id")
    int countDepartmentById(@Param("id") Integer id);

    @Query(nativeQuery = true, name = "select count(*) from projects where organization_id = :id")
    int countProjectById(@Param("id") Integer id);
}
