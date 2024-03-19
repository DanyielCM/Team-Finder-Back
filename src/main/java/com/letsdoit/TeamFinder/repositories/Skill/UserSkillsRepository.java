package com.letsdoit.TeamFinder.repositories.Skill;

import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Skills.EmployeeSkills;
import com.letsdoit.TeamFinder.domain.Skills.UserSkills;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserSkillsRepository extends JpaRepository<UserSkills, Integer>
{
    Optional<UserSkills> findByEmployeeId(Employees employeeId);
    Optional<List<UserSkills>> findAllByEmployeeId(Employees employeeId);
    Optional<UserSkills> findBySkillId(EmployeeSkills skillId);
    void deleteAllBySkillId(EmployeeSkills skillId);
    Optional<Set<UserSkills>> findAllBySkillId(EmployeeSkills skillId);

}
