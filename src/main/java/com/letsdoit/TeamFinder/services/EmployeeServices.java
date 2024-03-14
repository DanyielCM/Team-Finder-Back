package com.letsdoit.TeamFinder.services;


import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.domain.Role;
import com.letsdoit.TeamFinder.repositories.DepartmentRepository;
import com.letsdoit.TeamFinder.repositories.EmployeeRepository;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;
import com.letsdoit.TeamFinder.repositories.RoleRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Log
@Service
public class EmployeeServices {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final OrganizationRepository organizationRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServices(EmployeeRepository employeeRepository, RoleRepository roleRepository, OrganizationRepository organizationRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.organizationRepository = organizationRepository;
        this.departmentRepository = departmentRepository;
    }

    public void addRoleForEmployee(Integer employeeId, String role) {
        try
        {
            employeeRepository.findById(employeeId).orElseThrow(()-> {throw new IllegalStateException("Employee with id " + employeeId + " does not exist");});
            roleRepository.findByAuthority(role).orElseThrow(()-> {throw new IllegalStateException("Role " + role + " does not exist");});
            try{
                Employees employee = employeeRepository.findById(employeeId).get();
                employee.getAuthorities().add(roleRepository.findByAuthority(role).get());
                employeeRepository.save(employee);

            }
            catch (Exception e){
                throw new IllegalStateException("Failed to add role to employee");
            }
        }
        catch (IllegalStateException e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void removeRoleForEmployee(Integer employeeId, String role) {
        try
        {
            employeeRepository.findById(employeeId).orElseThrow(()-> {throw new IllegalStateException("Employee with id " + employeeId + " does not exist");});
            roleRepository.findByAuthority(role).orElseThrow(()-> {throw new IllegalStateException("Role " + role + " does not exist");});
            try{
                Employees employee = employeeRepository.findById(employeeId).get();
                employee.getAuthorities().remove(roleRepository.findByAuthority(role).get());
                employeeRepository.save(employee);

            }
            catch (Exception e){
                throw new IllegalStateException("Failed to remove role from employee");
            }
        }
        catch (IllegalStateException e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public List<Employees> getEmployees(Organization organization) {
        try{
            return employeeRepository.findAllByOrganization(organization);
        }
        catch (Exception e){
            throw new IllegalStateException("Failed to get employees");
        }
    }

    public Set<Role> getRoleForEmployee(Integer employeeId) {
        try
        {
            employeeRepository.findById(employeeId).orElseThrow(()-> {throw new IllegalStateException("Employee with id " + employeeId + " does not exist");});
            try{
                Employees employee = employeeRepository.findById(employeeId).get();
                Set<Role> roles = employee.getAuthorities();
                return roles;
            }
            catch (Exception e){
                throw new IllegalStateException("Failed to get role for employee");
            }
        }
        catch (IllegalStateException e){
            throw new IllegalStateException(e.getMessage());
        }
    }




}
