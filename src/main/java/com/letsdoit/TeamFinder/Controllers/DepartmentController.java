package com.letsdoit.TeamFinder.Controllers;

import com.letsdoit.TeamFinder.domain.DTO.DepartmentDTO;
import com.letsdoit.TeamFinder.domain.DTO.EmployeeDTO;
import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.services.DepartmentServices;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity createDepartment(@RequestBody DepartmentDTO department){
        try{
            
            Department departmentObj = new Department(department.getDepartmentName(), department.getDepartmentDescription(), department.getDepartmentManager(), department.getOrganizationId());
            departmentServices.createDepartment(departmentObj);
            return ResponseEntity.status(201).body("Department created successfully");
        }
        catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(500).body("Failed to create resource" + e.getMessage());
        }
    }

    // This method is used to get a department
    @GetMapping("/getDepartments/{orgId}")
    public ResponseEntity<List<Department>> getDepartment(@PathVariable("orgId") Integer id) {
        try{
            Organization organization = new Organization();
            organization.setOrganizationId(id);
            return new ResponseEntity<>(departmentServices.getDepartments(organization), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getEmployeesFromDepartment/{departmentId}")
    public ResponseEntity getEmployeesFromDepartment(@PathVariable("departmentId") Integer departmentId) {
        try{
            List<Employees> employees = departmentServices.getEmployeesFromDepartment(departmentId);
            List<EmployeeDTO> employeesDTO = employees.stream().map(employee -> new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeUserName(), employee.getEmployeeEmail(), employee.getOrganization().getOrganizationId(), employee.getProjecthours())).toList();
            return ResponseEntity.status(200).body(employeesDTO);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to get employees from department" + e.getMessage());
        }
    }

   //Change department manager
    @PostMapping("/changeDepartmentManager")
    public ResponseEntity changeDepartmentManager(@RequestParam("department") Integer departmentId, @RequestParam("newManager") Integer managerId) {
        try{
            departmentServices.changeDepartmentManager(departmentId, managerId);
            return ResponseEntity.status(200).body("Department manager changed successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to change department manager" + e.getMessage());
        }
    }


    @DeleteMapping("/removeEmployeeFromDepartment/{employeeId}/{departmentId}")
    public ResponseEntity removeEmployeeFromDepartment(@PathVariable("employeeId") Integer employeeId, @PathVariable("departmentId") Integer departmentId){
        try{
            departmentServices.removeEmployeeFromDepartment(employeeId, departmentId);
            return ResponseEntity.status(200).body("Employee removed from department successfully");
        }
        catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(500).body("Failed to remove employee from department" + e.getMessage());
        }
    }

    @GetMapping("/getUnassignedEmployees")
    public ResponseEntity getUnassignedEmployees(@RequestParam Integer orgId, Principal principal){
        try{
            return ResponseEntity.status(200).body(departmentServices.getUnassignedEmployees(orgId, principal.getName()));
        }
        catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(500).body("Failed to get unassigned employees" + e.getMessage());
        }
    }

    @GetMapping("/getUnassignedDepartmentManagers")
    public ResponseEntity getUnassignedDepartmentManagers(@RequestParam Integer orgId){
        try{
            return ResponseEntity.status(200).body(departmentServices.getUnassignedDepartmentManagers(orgId));
        }
        catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(500).body("Failed to get unassigned department managers" + e.getMessage());
        }
    }


    @PostMapping("/assignEmployeeToDepartment/{employeeId}/{departmentId}")
    public ResponseEntity assignEmployeeToDepartment(@PathVariable("employeeId") Integer employeeId, @PathVariable("departmentId") Integer departmentId){
        try{
            departmentServices.assignEmployeeToDepartment(employeeId, departmentId);
            return ResponseEntity.status(200).body("Employee assigned to department successfully");
        }
        catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(500).body("Failed to assign employee to department" + e.getMessage());
        }
    }

    @PostMapping("/updateDepartmentName")
    public ResponseEntity updateDepartmentName(@RequestParam("department") Integer departmentId, @RequestParam("newName") String newName) {
        try{
            departmentServices.updateDepartmentName(departmentId, newName);
            return ResponseEntity.status(200).body("Department name updated successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to update department name" + e.getMessage());
        }
    }

    @PostMapping("/updateDepartmentDescription")
    public ResponseEntity updateDepartmentDescription(@RequestParam("department") Integer departmentId, @RequestParam("newDescription") String newDescription) {
        try{
            departmentServices.updateDepartmentDescription(departmentId, newDescription);
            return ResponseEntity.status(200).body("Department description updated successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Failed to update department description" + e.getMessage());
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
            return ResponseEntity.status(500).body("Failed to delete resource" + e.getMessage());
        }
    }





}
