package com.organization.employeeManagement.repository;

import com.organization.employeeManagement.entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {
    List<Project> findAllByOrganizationId(int orgId);
}
