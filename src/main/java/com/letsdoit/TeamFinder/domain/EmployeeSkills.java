package com.letsdoit.TeamFinder.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "employee_skills")
@Entity
public class EmployeeSkills {
    @Id
    @Column(name = "employee_skill_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeSkillId;
    @Column(name = "proficiency_level")
    private Integer proficiencyLevel;
    //de ex. 5 ani, 3 luni etc.
    private String experience;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employees employee;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id", referencedColumnName = "employee_skill_id")
    private Skills skill;

    public EmployeeSkills(Integer employeeSkillId, Integer proficiencyLevel, String experience, Employees employee, Skills skill) {
        this.employeeSkillId = employeeSkillId;
        this.proficiencyLevel = proficiencyLevel;
        this.experience = experience;
        this.employee = employee;
        this.skill = skill;
    }

    public EmployeeSkills() {

    }
}
