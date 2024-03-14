package com.letsdoit.TeamFinder.services;

import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Skills.EmployeeSkills;
import com.letsdoit.TeamFinder.domain.Skills.SkillCategory;
import com.letsdoit.TeamFinder.repositories.DepartmentRepository;
import com.letsdoit.TeamFinder.repositories.EmployeeRepository;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;
import com.letsdoit.TeamFinder.repositories.Skill.EmployeeSkillsRepository;
import com.letsdoit.TeamFinder.repositories.Skill.SkillCategoryRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.access.InvalidInvocationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class SkillsServices {

    private final SkillCategoryRepository skillCategoryRepository;
    private final EmployeeSkillsRepository employeeSkillsRepository;
    private final OrganizationRepository organizationRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employees;

    @Autowired
    public SkillsServices(DepartmentRepository departmentRepository, EmployeeRepository employees, SkillCategoryRepository skillCategoryRepository, OrganizationRepository organizationRepository, EmployeeSkillsRepository employeeSkillsRepository) {
        this.skillCategoryRepository = skillCategoryRepository;
        this.departmentRepository = departmentRepository;
        this.employeeSkillsRepository = employeeSkillsRepository;
        this.organizationRepository = organizationRepository;
        this.employees = employees;
    }

    public void addSkillCategory(String skillCategoryName, Integer organizationId) {
        if (skillCategoryRepository.findBySkillCategoryNameAndOrganizationId(skillCategoryName, organizationRepository.findById(organizationId).orElseThrow(() -> new InvalidInvocationException("Organization not found"))).isPresent())
            throw new InvalidInvocationException("Skill category already exists");
        skillCategoryRepository.save(new SkillCategory(skillCategoryName, organizationRepository.findById(organizationId).orElseThrow(() -> new InvalidInvocationException("Organization not found"))));
    }

    public List<SkillCategory> getSkillCategories(Integer organizationId) {
        return skillCategoryRepository.findAllByOrganizationIdOrSkillCategoryNameNotNull(organizationRepository.findById(organizationId).orElseThrow(() -> new InvalidInvocationException("Organization not found")));
    }

    public void removeSkillCategory(Integer skillCategoryId) {
        skillCategoryRepository.deleteById(skillCategoryId);
    }

    public void updateSkillCategoryName(Integer skillCategoryId, String newSkillCategoryName) {
        SkillCategory skillCategory = skillCategoryRepository.findById(skillCategoryId).orElseThrow(() -> new InvalidInvocationException("Skill category not found"));
        skillCategory.setSkillCategoryName(newSkillCategoryName);
        skillCategoryRepository.save(skillCategory);
    }

    //-------------------Skill Employee Services-------------------


    public void addSkillsToCategory(String skillName, String skillDescription, Integer employeeId, Integer skillCategoryId, Integer departmentId) {
        employeeSkillsRepository.findAllBySkillNameAndDepartment(skillName, departmentRepository.findById(departmentId).orElseThrow(() -> new InvalidInvocationException("Department not found"))).ifPresent(employeeSkills -> {
            throw new InvalidInvocationException("Skill already exists");
        });
        employeeSkillsRepository.save(new EmployeeSkills(skillName, skillDescription, employees.findById(employeeId).orElseThrow(() -> new InvalidInvocationException("Employee not found")),
                skillCategoryRepository.findById(skillCategoryId).orElseThrow(() -> new InvalidInvocationException("Skill category not found")),
                departmentRepository.findById(departmentId).orElseThrow(() -> new InvalidInvocationException("Department not found"))));
    }

    /*public List<EmployeeSkills> getSkillsByCategory(Integer skillCategoryId) {
        SkillCategory skillCategory = skillCategoryRepository.findById(skillCategoryId).orElseThrow(() -> new InvalidInvocationException("Skill category not found"));
        //Department department= departmentRepository.findById(departmentId).orElseThrow(() -> new InvalidInvocationException("Department not found"));
        //log.info(employeeSkillsRepository.findAllBySkillCategoryIdAndDepartment(skillCategory).toString());
        return employeeSkillsRepository.findAllBySkillCategoryId(skillCategory);
    }*/


    public List<EmployeeSkills> getSkillsByDepartmentAndCategory(Integer departmentId, Integer skillCategoryId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new InvalidInvocationException("Department not found"));
        SkillCategory skillCategory = skillCategoryRepository.findById(skillCategoryId).orElseThrow(() -> new InvalidInvocationException("Skill category not found"));
        return employeeSkillsRepository.findAllByDepartmentAndSkillCategoryId(department, skillCategory).orElseThrow(() -> new InvalidInvocationException("Skills not found"));
    }

    public void removeSkill(Integer skillId) {
        employeeSkillsRepository.deleteById(skillId);
    }

    public void updateSkill(Integer skillId, String skillName, String skillDescription) {
        EmployeeSkills employeeSkills = employeeSkillsRepository.findById(skillId).orElseThrow(() -> new InvalidInvocationException("Skill not found"));
        employeeSkills.setSkillName(skillName);
        employeeSkills.setSkillDescription(skillDescription);
        employeeSkillsRepository.save(employeeSkills);
    }

}