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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id")
    private EmployeeSkills skillId;
    private Integer proficiencyLevel;
    private String experience;

    public UserSkills(Employees employeeId, EmployeeSkills skillId, Integer proficiencyLevel, String experience) {
        this.employeeId = employeeId;
        this.skillId = skillId;
        this.proficiencyLevel = proficiencyLevel;
        this.experience = experience;
    }



}
