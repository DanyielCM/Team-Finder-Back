package com.letsdoit.TeamFinder.services;

import com.letsdoit.TeamFinder.domain.DTO.ProjectDTO;
import com.letsdoit.TeamFinder.domain.DTO.ProjectMemberDTO;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Project;
import com.letsdoit.TeamFinder.domain.Skills.EmployeeSkills;
import com.letsdoit.TeamFinder.domain.Skills.UserSkills;
import com.letsdoit.TeamFinder.repositories.EmployeeRepository;
import com.letsdoit.TeamFinder.repositories.ProjectRepository;
import com.letsdoit.TeamFinder.repositories.Skill.EmployeeSkillsRepository;
import com.letsdoit.TeamFinder.repositories.Skill.UserSkillsRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log
public class ProjectServices {
    private final ProjectRepository projectRepository;
    private final EmployeeSkillsRepository employeeSkillsRepository;
    private final UserSkillsRepository userSkillsRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ProjectServices(EmployeeRepository employeeRepository, ProjectRepository projectRepository, EmployeeSkillsRepository employeeSkillsRepository, UserSkillsRepository userSkillsRepository) {
        this.projectRepository = projectRepository;
        this.employeeSkillsRepository = employeeSkillsRepository;
        this.userSkillsRepository = userSkillsRepository;
        this.employeeRepository = employeeRepository;
    }

    public void createProject(ProjectDTO project, Principal principal) {
        try {
                Project newProject = new Project(project.getName(), project.getStatus(), project.getStartDate(), project.getEndDate(),project.getProjectPeriod(), employeeRepository.findByEmployeeUserName(principal.getName()).get());
                projectRepository.save(newProject);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public void deleteProject(Integer projectID) {
        projectRepository.deleteById(projectID);
    }

    public void addTechStack(Integer projectID, Integer techStack) {
        Project project = projectRepository.findById(projectID).get();
        EmployeeSkills tech = employeeSkillsRepository.findById(techStack).get();
        project.getEmployeeSkills().add(tech);
        projectRepository.save(project);
    }

    public Set<ProjectMemberDTO> findMembers(Integer projectID){
        Set<EmployeeSkills> techStack = projectRepository.findById(projectID).get().getEmployeeSkills();
        Set<ProjectMemberDTO> members = new HashSet<>();
        for (EmployeeSkills tech : techStack) {
            UserSkills userSkills = userSkillsRepository.findBySkillId(tech).get();
            members.add(new ProjectMemberDTO(userSkills.getEmployeeId().getEmployeeId(), userSkills.getEmployeeId().getEmployeeUserName()));
        }
        log.info(members.toString());
        for(ProjectMemberDTO member : members){
           List<UserSkills> userSkill = userSkillsRepository.findAllByEmployeeId(employeeRepository.findById(member.getEmployeeID()).get()).get();
           for(UserSkills skill : userSkill){
               member.getEmployeeSkills().add(skill.getSkillId().getSkillName());
           }
        }

        return members;
    }
    }
