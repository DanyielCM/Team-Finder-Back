package com.letsdoit.TeamFinder.Controllers;

import com.letsdoit.TeamFinder.domain.Role;
import com.letsdoit.TeamFinder.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    @Autowired
    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @PostMapping("/addRole/{id}/{role}")
    public ResponseEntity updateRole(@PathVariable("id") Integer id, @PathVariable("role") String role) {
        try{
            employeeServices.addRoleForEmployee(id, role);
            return ResponseEntity.status(200).body("Role added successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to add role");
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
