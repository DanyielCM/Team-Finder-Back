package com.letsdoit.TeamFinder.Controllers;

import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.domain.Role;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;
import com.letsdoit.TeamFinder.services.EmployeeServices;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Log
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeServices employeeServices;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public EmployeeController(EmployeeServices employeeServices, OrganizationRepository organizationRepository) {
        this.employeeServices = employeeServices;
        this.organizationRepository = organizationRepository;
    }

    @PostMapping("/addRole/{id}/{role}")
    public ResponseEntity addRole(@PathVariable("id") Integer id, @PathVariable("role") String role) {
        try{
            employeeServices.addRoleForEmployee(id, role);
            return ResponseEntity.status(200).body("Role added successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/getRole/{id}")
    public ResponseEntity getRole(@PathVariable("id") Integer id) {
        try{
            Set<Role> role = employeeServices.getRoleForEmployee(id);
            return ResponseEntity.status(200).body(role);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to get role");
        }
    }

    @GetMapping("/getEmployees")
    public ResponseEntity getEmployees(@RequestParam Integer orgId) {
        try{
            Organization organization = organizationRepository.findById(orgId).orElseThrow(()-> {throw new IllegalStateException("Organization with id " + orgId + " does not exist");});
            return ResponseEntity.status(200).body(employeeServices.getEmployees(organization));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to get employees");
        }
    }

    @DeleteMapping("/removeRole/{id}/{role}")
    public ResponseEntity removeRole(@PathVariable("id") Integer id, @PathVariable("role") String role) {
        try{
            employeeServices.removeRoleForEmployee(id, role);
            return ResponseEntity.status(200).body("Role removed successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to remove role");
        }
    }


}
