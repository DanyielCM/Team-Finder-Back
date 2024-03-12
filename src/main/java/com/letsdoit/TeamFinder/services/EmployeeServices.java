package com.letsdoit.TeamFinder.services;


import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Role;
import com.letsdoit.TeamFinder.repositories.EmployeeRepository;
import com.letsdoit.TeamFinder.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmployeeServices {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public EmployeeServices(EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
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
