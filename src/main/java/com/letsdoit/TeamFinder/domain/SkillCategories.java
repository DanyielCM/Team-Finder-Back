package com.letsdoit.TeamFinder.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Table(name = "skill_categories")
@Data
@Entity
public class SkillCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skill_category_id")
    private Integer skillCategoryId;
    @Column(name = "skill_category_name")
    private String skillCategoryName;
    @JoinColumn(name = "organiztion_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Organization organization;
    @ManyToMany(mappedBy = "skillCategoriesSet")
    private Set<Skills> skillsSet;

    public SkillCategories(Integer skillCategoryId, String skillCategoryName, Organization organization, Set<Skills> skillsSet) {
        this.skillCategoryId = skillCategoryId;
        this.skillCategoryName = skillCategoryName;
        this.organization = organization;
        this.skillsSet = skillsSet;
    }

    public SkillCategories() {

    }
}
