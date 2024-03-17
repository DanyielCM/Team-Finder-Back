package com.letsdoit.TeamFinder.repositories;

import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// This interface is used to interact with the database with CRUD operations
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    public List<Department> findAllByOrganizationId(Organization organizationId);
    public Optional<Department> findByDepartmentId(Integer departmentId);
}
