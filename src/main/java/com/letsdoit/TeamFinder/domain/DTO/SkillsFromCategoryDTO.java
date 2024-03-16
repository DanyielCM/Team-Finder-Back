package com.letsdoit.TeamFinder.domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Skills.SkillCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillsFromCategoryDTO {
    private Integer skillId;
    private String skillName;
    private String skillDescription;
    @JsonIgnore
    private Employees authorName;
    @JsonIgnore
    private SkillCategory skillCategoryId;
    @JsonIgnore
    private Department department;

    public SkillsFromCategoryDTO(Integer skillId, String skillName, String skillDescription, Employees employeeId, Department department) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
        this.authorName = employeeId;
        this.department = department;
    }
}
