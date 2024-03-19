package com.letsdoit.TeamFinder.repositories;

import com.letsdoit.TeamFinder.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
