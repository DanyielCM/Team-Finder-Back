package com.letsdoit.TeamFinder.repositories;

import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Set<Project>> findAllByProjectManager(Employees employee);
}
