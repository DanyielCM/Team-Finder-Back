package com.letsdoit.TeamFinder.repositories;

import com.letsdoit.TeamFinder.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This interface is used to interact with the database with CRUD operations
@Repository
public interface DepartamentRepository extends JpaRepository<Department, Integer>{
}
