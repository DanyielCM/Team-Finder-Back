package com.letsdoit.TeamFinder.domain.Skills;

import com.letsdoit.TeamFinder.domain.Employees;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "user_skills")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userSkillId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employees employeeId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_skills_employee_skills",
            joinColumns = @JoinColumn(name = "user_skill_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<EmployeeSkills> skillId;
    private Integer proficiencyLevel;
    private String experience;

    public UserSkills(Employees employeeId, Set<EmployeeSkills> skillId, Integer proficiencyLevel, String experience) {
        this.employeeId = employeeId;
        this.skillId = skillId;
        this.proficiencyLevel = proficiencyLevel;
        this.experience = experience;
    }



}
