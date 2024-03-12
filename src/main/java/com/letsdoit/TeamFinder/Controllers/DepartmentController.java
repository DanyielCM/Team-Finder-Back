package com.letsdoit.TeamFinder.Controllers;

import com.letsdoit.TeamFinder.domain.DTO.DepartmentDTO;
import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.services.DepartmentServices;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/api")
public class DepartmentController {

    // This class is used to create the endpoints for the department
    private final DepartmentServices departmentServices;
    @Autowired
    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    // This method is used to create a department
    @PostMapping("/createDepartment")
    public ResponseEntity createDepartament(@RequestBody DepartmentDTO department){
        try{
            Department departmentObj = new Department(department.getDepartmentName(), department.getDepartmentDescription(), department.getDepartmentManager(), department.getOrganizationId());
            departmentServices.createDepartment(departmentObj);
            return ResponseEntity.status(201).body("Department created successfully");
        }
        catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(500).body("Failed to create resource");
        }
    }

    // This method is used to get a department
    @GetMapping("/getDepartments/{orgId}")
    public ResponseEntity<List<Department>> getDepartament(@PathVariable("orgId") Integer id) {
        try{
            Organization organization = new Organization();
            organization.setOrganizationId(id);
            return new ResponseEntity<>(departmentServices.getDepartments(organization), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // This method is used to delete a department
    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity deleteDepartment(@PathVariable("id") Integer id) {
        try{
            departmentServices.deleteDepartment(id);
            return ResponseEntity.status(200).body("Resource deleted successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to delete resource");
        }
    }





}