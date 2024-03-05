package com.letsdoit.TeamFinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.letsdoit.TeamFinder.domain.Employees;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {
    Optional<Employees> findByEmployeeUserName(String userName);
    Optional<Employees> findByEmployeeEmail(String email);
}
