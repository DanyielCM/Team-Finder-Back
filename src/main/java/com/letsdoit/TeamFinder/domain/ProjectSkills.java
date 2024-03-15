package com.letsdoit.TeamFinder.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "project_skills")
@Entity
public class ProjectSkills {
    @Column(name = "project_skill_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer Id;
    private String projectSkillName;

    public ProjectSkills(Integer project_skill_id, String project_skill_name) {
        this.Id = project_skill_id;
        this.projectSkillName = project_skill_name;
    }

    public ProjectSkills() {

    }
}
