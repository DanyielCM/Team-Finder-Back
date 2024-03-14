package com.letsdoit.TeamFinder.repositories.Skill;

import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.domain.Skills.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Integer> {
    Optional<SkillCategory> findBySkillCategoryName(String skillCategoryName);
    List<SkillCategory> findAllByOrganizationIdOrSkillCategoryNameNotNull(Organization organizationId);
    Optional<SkillCategory> findBySkillCategoryNameAndOrganizationId(String skillCategoryName, Organization organizationId);
}
