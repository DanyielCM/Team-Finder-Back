package com.letsdoit.TeamFinder.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillsThatAUserHaveDTO {
    private Integer userSkillId;
    private String skills;
    private Integer proficiencyLevel;
    private String experience;
}
