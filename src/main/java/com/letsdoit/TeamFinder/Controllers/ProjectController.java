package com.letsdoit.TeamFinder.Controllers;

import com.letsdoit.TeamFinder.domain.DTO.ProjectDTO;
import com.letsdoit.TeamFinder.domain.DTO.ProjectMemberDTO;
import com.letsdoit.TeamFinder.domain.Project;
import com.letsdoit.TeamFinder.services.ProjectServices;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Log
@PreAuthorize("hasAnyRole('OrganizationAdmin, ProjectManager')")
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectServices projectServices;

    @Autowired
    public ProjectController(ProjectServices projectServices) {
        this.projectServices = projectServices;
    }
    @PostMapping("/create")
    public ResponseEntity createProject(@RequestBody ProjectDTO project, Principal principal) {
        try {
            projectServices.createProject(project, principal);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/addTechStack")
    public ResponseEntity addTechStack(@RequestParam Integer projectID, @RequestParam Integer techStackID) {
        try {

            projectServices.addTechStack(projectID, techStackID);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteProject(@RequestParam Integer projectID) {
        try {
            projectServices.deleteProject(projectID);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findMembers")
    public ResponseEntity findMembers(@RequestParam Integer projectID) {
        try {
            return ResponseEntity.ok(projectServices.findMembers(projectID));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
