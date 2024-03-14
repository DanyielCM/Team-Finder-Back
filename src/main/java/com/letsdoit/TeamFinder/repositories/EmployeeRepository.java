package com.letsdoit.TeamFinder.repositories;

import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.letsdoit.TeamFinder.domain.Employees;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {
    Optional<Employees> findByEmployeeUserName(String userName);
    Optional<Employees> findByEmployeeEmail(String email);
   // List<Employees> findAllByOrganizationAndAuthorities(Organization organization, Set<Role> role);
    List<Employees> findAllByOrganization(Organization organization);
    List<Employees> findAllByOrganizationAndDepartmentsEmptyAndAuthoritiesNotContaining(Organization organization, Role role);
}
