package com.letsdoit.TeamFinder.Controllers;

import com.letsdoit.TeamFinder.domain.DTO.SkillsFromCategoryDTO;
import com.letsdoit.TeamFinder.domain.DTO.SkillsThatAUserHaveDTO;
import com.letsdoit.TeamFinder.domain.Skills.EmployeeSkills;
import com.letsdoit.TeamFinder.domain.Skills.SkillCategory;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;
import com.letsdoit.TeamFinder.repositories.Skill.SkillCategoryRepository;
import com.letsdoit.TeamFinder.services.SkillsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillsServices skillsServices;

    @Autowired
    public SkillController(SkillsServices skillsServices) {
        this.skillsServices = skillsServices;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/getSkillCategories/{organizationId}")
    public ResponseEntity getSkillCategories(@PathVariable("organizationId") Integer organizationId) {
        try{
            return ResponseEntity.status(200).body(skillsServices.getSkillCategories(organizationId));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to get skill categories " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('OrganizationAdmin', 'DepartmentManager')")
    @PostMapping("/addSkillCategory/{skillCategoryName}/{organizationId}")
    public ResponseEntity addSkillCategory(@PathVariable("skillCategoryName") String skillCategoryName, @PathVariable("organizationId") Integer organizationId) {
        try{
        skillsServices.addSkillCategory(skillCategoryName, organizationId);
        return ResponseEntity.status(200).body("Skill category added successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to add skill category: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('OrganizationAdmin', 'DepartmentManager')")
    @DeleteMapping("/removeSkillCategory/{skillCategoryId}")
    public ResponseEntity removeSkillCategory(@PathVariable("skillCategoryId") Integer skillCategoryId) {
        try{
            skillsServices.removeSkillCategory(skillCategoryId);
            return ResponseEntity.status(200).body("Skill category removed successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to remove skill category" + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('OrganizationAdmin', 'DepartmentManager')")
    @PostMapping("/updateSkillCategoryName/{skillCategoryId}/{newSkillCategoryName}")
    public ResponseEntity updateSkillCategoryName(@PathVariable("skillCategoryId") Integer skillCategoryId, @PathVariable("newSkillCategoryName") String newSkillCategoryName) {
        try{
            skillsServices.updateSkillCategoryName(skillCategoryId, newSkillCategoryName);
            return ResponseEntity.status(200).body("Skill category name updated successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to update skill category name" + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('OrganizationAdmin', 'DepartmentManager')")
    @PostMapping("/addSkillsToCategory/{skillName}/{skillDescription}/{employeeId}/{skillCategoryId}/{departmentId}")
    public ResponseEntity addSkillsToCategory(@PathVariable("skillName") String skillName, @PathVariable("skillDescription") String skillDescription, @PathVariable("employeeId") Integer employeeId, @PathVariable("skillCategoryId") Integer skillCategoryId, @PathVariable("departmentId") Integer departmentId){
        try{
            skillsServices.addSkillsToCategory(skillName, skillDescription, employeeId, skillCategoryId, departmentId);
            return ResponseEntity.status(200).body("Skill added successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to add skill: " + e.getMessage());
        }
    }

    //TODO: ADD DTOs


    @PreAuthorize("permitAll()")
    @GetMapping("/getSkillsByDepartmentAndCategory/{departmentId}/{skillCategoryId}")
    public ResponseEntity getSkillsByDepartment(@PathVariable("departmentId") Integer departmentId, @PathVariable("skillCategoryId") Integer skillCategoryId){
        try{
            return ResponseEntity.status(200).body(skillsServices.getSkillsByDepartmentAndCategory(departmentId, skillCategoryId));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to get skills by department " + e.getMessage());
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/getSkillsByOrganization/{organizationId}")
    public ResponseEntity getSkillsByOrganization(@PathVariable("organizationId") Integer organizationId){
        try{
            return ResponseEntity.status(200).body(skillsServices.getSkillsByOrganization(organizationId));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to get skills by organization " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('OrganizationAdmin', 'DepartmentManager')")
    @DeleteMapping("/removeSkill/{skillId}")
    public ResponseEntity removeSkill(@PathVariable("skillId") Integer skillId) {
        try{
            skillsServices.removeSkill(skillId);
            return ResponseEntity.status(200).body("Skill removed successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to remove skill" + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('OrganizationAdmin', 'DepartmentManager')")
    @PostMapping("/updateSkill/{skillId}/{newSkillName}/{newSkillDescription}")
    public ResponseEntity updateSkill(@PathVariable("skillId") Integer skillId, @PathVariable("newSkillName") String newSkillName, @PathVariable("newSkillDescription") String newSkillDescription) {
        try{
            skillsServices.updateSkill(skillId, newSkillName, newSkillDescription);
            return ResponseEntity.status(200).body("Skill updated successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to update skill" + e.getMessage());
        }
    }

    //---------------------------User Skills---------------------------


    @PreAuthorize("permitAll()")
    @PostMapping("/addUserSkills/{employeeId}/{skillId}/{proficiencyLevel}/{experience}")
    public ResponseEntity addUserSkills(@PathVariable("employeeId") Integer employeeId, @PathVariable("skillId") Integer skillId, @PathVariable("proficiencyLevel") Integer proficiencyLevel, @PathVariable("experience") String experience) {
        try{
            if(proficiencyLevel < 1 || proficiencyLevel > 5)
                return ResponseEntity.status(400).body("Proficiency level must be between 1 and 5");
            skillsServices.addUserSkills(employeeId, skillId, proficiencyLevel, experience);
            return ResponseEntity.status(200).body("User skills added successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to add user skills" + e.getMessage());
        }
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping("/removeUserSkill/{userSkillId}")
    public ResponseEntity removeUserSkill(@PathVariable("userSkillId") Integer userSkillId) {
        try{
            skillsServices.removeUserSkill(userSkillId);
            return ResponseEntity.status(200).body("User skill removed successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to remove user skill" + e.getMessage());
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/getUserSkills/{employeeId}")
    public ResponseEntity getUserSkills(@PathVariable("employeeId") Integer employeeId) {
        try{
            Set<SkillsThatAUserHaveDTO> skillsThatAUserHaveDTO;
            skillsThatAUserHaveDTO = skillsServices.getSkillsByEmployee(employeeId);
            return ResponseEntity.status(200).body(skillsThatAUserHaveDTO);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to get user skills" + e.getMessage());
        }
    }

}
