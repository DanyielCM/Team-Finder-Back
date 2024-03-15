package com.letsdoit.TeamFinder.domain.DTO;

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
    private String authorName;
    private SkillCategory skillCategoryId;
    private Department department;
}
