package com.letsdoit.TeamFinder.repositories.Skill;

import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Skills.EmployeeSkills;
import com.letsdoit.TeamFinder.domain.Skills.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeSkillsRepository extends JpaRepository<EmployeeSkills, Integer> {
    Optional<List<EmployeeSkills>> findAllByDepartmentAndSkillCategoryId(Department department, SkillCategory skillCategory);
    Optional<EmployeeSkills> findAllBySkillNameAndDepartment(String skillName, Department department);

}
