package com.letsdoit.TeamFinder.domain.Skills;

import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Employees;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee_skills")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skill_id")
    private Integer skillId;
    private String skillName;
    private String skillDescription;
    @JoinColumn(name = "employee_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Employees employeeId;
    @JoinColumn(name = "skill_category_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private SkillCategory skillCategoryId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;


    public EmployeeSkills(String skillName, String skillDescription, Employees employeeId, SkillCategory skillCategoryId, Department department) {
        this.skillName = skillName;
        this.skillDescription = skillDescription;
        this.employeeId = employeeId;
        this.skillCategoryId = skillCategoryId;
        this.department = department;
    }
}
