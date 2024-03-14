package com.letsdoit.TeamFinder.Controllers;

import com.letsdoit.TeamFinder.domain.DTO.SkillsFromCategoryDTO;
import com.letsdoit.TeamFinder.domain.Skills.EmployeeSkills;
import com.letsdoit.TeamFinder.domain.Skills.SkillCategory;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;
import com.letsdoit.TeamFinder.repositories.Skill.SkillCategoryRepository;
import com.letsdoit.TeamFinder.services.SkillsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillsServices skillsServices;

    @Autowired
    public SkillController(SkillsServices skillsServices) {
        this.skillsServices = skillsServices;
    }

    @GetMapping("/getSkillCategories/{organizationId}")
    public ResponseEntity getSkillCategories(@PathVariable("organizationId") Integer organizationId) {
        try{
            return ResponseEntity.status(200).body(skillsServices.getSkillCategories(organizationId));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to get skill categories");
        }
    }

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

    @DeleteMapping("/removeSkillCategory/{skillCategoryId}")
    public ResponseEntity removeSkillCategory(@PathVariable("skillCategoryId") Integer skillCategoryId) {
        try{
            skillsServices.removeSkillCategory(skillCategoryId);
            return ResponseEntity.status(200).body("Skill category removed successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to remove skill category");
        }
    }

    @PostMapping("/updateSkillCategoryName/{skillCategoryId}/{newSkillCategoryName}")
    public ResponseEntity updateSkillCategoryName(@PathVariable("skillCategoryId") Integer skillCategoryId, @PathVariable("newSkillCategoryName") String newSkillCategoryName) {
        try{
            skillsServices.updateSkillCategoryName(skillCategoryId, newSkillCategoryName);
            return ResponseEntity.status(200).body("Skill category name updated successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to update skill category name");
        }
    }

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

    /*@GetMapping("/getSkillsByCategoryAndDepartment/{skillCategoryId}/{departmentId}")
    public ResponseEntity getSkillsByCategory(@PathVariable("skillCategoryId") Integer skillCategoryId, @PathVariable("departmentId") Integer departmentId){
        try{
            return ResponseEntity.status(200).body(skillsServices.getSkillsByCategory(skillCategoryId));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to get skills by category " + e.getMessage());
        }
    }*/


    @GetMapping("/getSkillsByDepartmentAndCategory/{departmentId}/{skillCategoryId}")
    public ResponseEntity getSkillsByDepartment(@PathVariable("departmentId") Integer departmentId, @PathVariable("skillCategoryId") Integer skillCategoryId){
        try{
            return ResponseEntity.status(200).body(skillsServices.getSkillsByDepartmentAndCategory(departmentId, skillCategoryId));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to get skills by department " + e.getMessage());
        }
    }

    @DeleteMapping("/removeSkill/{skillId}")
    public ResponseEntity removeSkill(@PathVariable("skillId") Integer skillId) {
        try{
            skillsServices.removeSkill(skillId);
            return ResponseEntity.status(200).body("Skill removed successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to remove skill");
        }
    }

    @PostMapping("/updateSkill/{skillId}/{newSkillName}/{newSkillDescription}")
    public ResponseEntity updateSkill(@PathVariable("skillId") Integer skillId, @PathVariable("newSkillName") String newSkillName, @PathVariable("newSkillDescription") String newSkillDescription) {
        try{
            skillsServices.updateSkill(skillId, newSkillName, newSkillDescription);
            return ResponseEntity.status(200).body("Skill updated successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to update skill");
        }
    }

}
