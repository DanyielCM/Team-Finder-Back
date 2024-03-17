package com.letsdoit.TeamFinder.domain.Skills;

import com.letsdoit.TeamFinder.domain.Organization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@Table(name = "skill_category")
@NoArgsConstructor
@AllArgsConstructor
public class SkillCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer skillCategoryId;
    private String skillCategoryName;
    @JoinColumn(name = "organization_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Organization organizationId;

    public SkillCategory(String skillCategoryName, Organization organizationId) {
        this.skillCategoryName = skillCategoryName;
        this.organizationId = organizationId;
    }

    public SkillCategory(String skillCategoryName) {
        this.skillCategoryName = skillCategoryName;
        this.organizationId = null;
    }

}
