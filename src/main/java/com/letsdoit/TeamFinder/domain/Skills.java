package com.letsdoit.TeamFinder.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Table(name = "skills")
@Data
@Entity
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer skillId;
    private String name;
    private String description;
    private String author;
    private String category;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "skill_categories_junction",
            joinColumns = @JoinColumn(name = "category"),
            inverseJoinColumns = @JoinColumn(name = "skill_category_id")
    )
    private Set<SkillCategories> skillCategoriesSet;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id")
    private EmployeeSkills userSkill;
    @ManyToMany(mappedBy = "skillsSet")
    private Set<Department> departments;

    public Skills(Integer skillId, String name, String description, String author, String category, Set<SkillCategories> skillCategoriesSet, EmployeeSkills userSkill, Set<Department> departments) {
        this.skillId = skillId;
        this.name = name;
        this.description = description;
        this.author = author;
        this.category = category;
        this.skillCategoriesSet = skillCategoriesSet;
        this.userSkill = userSkill;
        this.departments = departments;
    }

    public Skills() {

    }
}
