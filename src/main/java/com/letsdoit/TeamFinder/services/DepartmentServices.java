package com.letsdoit.TeamFinder.services;

import com.letsdoit.TeamFinder.domain.DTO.EmployeeDTO;
import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.repositories.DepartmentRepository;
import com.letsdoit.TeamFinder.repositories.EmployeeRepository;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;
import com.letsdoit.TeamFinder.repositories.RoleRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Log
public class DepartmentServices {
    // This class is a service that will be used to interact with the database
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public DepartmentServices(RoleRepository roleRepository ,DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, OrganizationRepository organizationRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
        this.roleRepository = roleRepository;
    }

    // This method will be used to create a new department
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // This method will be used to return a department
    public List<Department> getDepartments(Organization id) {
        return departmentRepository.findAllByOrganizationId(id);
    }

    public List<Employees> getEmployeesFromDepartment(Integer departmentId) {
        return employeeRepository.findAllByDepartments(departmentRepository.findByDepartmentId(departmentId).get());
    }

    // This method will be used to delete a department
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }

    public void changeDepartmentManager(Integer id, Integer managerId) {
        Department department = departmentRepository.findById(id).get();
        Employees manager = employeeRepository.findById(managerId).get();
        department.setDepartmentManager(manager);
        departmentRepository.save(department);
    }

    public void updateDepartmentName(Integer id, String name) {
        Department department = departmentRepository.findById(id).get();
        department.setDepartmentName(name);
        departmentRepository.save(department);
    }

    public void updateDepartmentDescription(Integer id, String description) {
        Department department = departmentRepository.findById(id).get();
        department.setDepartmentDescription(description);
        departmentRepository.save(department);
    }

    public List<Employees> getUnassignedEmployees(Integer orgId, String self){
        try{
            Employees employee = employeeRepository.findByEmployeeUserName(self).orElseThrow(()-> {throw new IllegalStateException("Employee with username " + self + " does not exist");});
            Organization organization = organizationRepository.findById(orgId).orElseThrow(()-> {throw new IllegalStateException("Organization with id " + orgId + " does not exist");});
            List<Employees> employees = employeeRepository.findAllByOrganizationAndDepartmentsEmptyAndAuthoritiesNotContaining(organization, roleRepository.findByAuthority("OrganizationAdmin").get());
            employees.remove(employee);
            return employees;
        }
        catch (Exception e){

            throw new IllegalStateException(e.getMessage());
        }
    }

    public List<EmployeeDTO> getUnassignedDepartmentManagers(Integer orgId)
    {
        try{
            Organization organization = organizationRepository.findById(orgId).orElseThrow(()-> {throw new IllegalStateException("Organization with id " + orgId + " does not exist");});
            List<Employees> assignedDepartmentManagers = departmentRepository.findAllByOrganizationId(organization).stream().map(Department::getDepartmentManager).toList();
            List<Employees> allDepartmentManagers = employeeRepository.findAllByOrganizationAndAuthoritiesContainingAndAuthoritiesIsNotContaining(organization, roleRepository.findByAuthority("DepartmentManager").get(), roleRepository.findByAuthority("OrganizationAdmin").get());
            List<Employees> unassignedDepartmentManagers = allDepartmentManagers.stream().filter(depManager -> !assignedDepartmentManagers.contains(depManager)).toList();
            List<EmployeeDTO> employeesDTO = unassignedDepartmentManagers.stream().map(employee -> new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeUserName(), employee.getEmployeeEmail(), employee.getOrganization().getOrganizationId(), employee.getProjecthours())).toList();
            return employeesDTO;
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void assignEmployeeToDepartment(Integer employeeId, Integer departmentId){
        try{
            Employees employee = employeeRepository.findById(employeeId).orElseThrow(()-> {throw new IllegalStateException("Employee with id " + employeeId + " does not exist");});
            Set<Department> departments = employee.getDepartments();
            departments.add(departmentRepository.findByDepartmentId(departmentId).orElseThrow(()-> {throw new IllegalStateException("Department with id " + departmentId + " does not exist");}));
            employee.setDepartments(departments);
            employeeRepository.save(employee);
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void removeEmployeeFromDepartment(Integer employeeId, Integer departmentId){
        try{
            Employees employee = employeeRepository.findById(employeeId).orElseThrow(()-> {throw new IllegalStateException("Employee with id " + employeeId + " does not exist");});
            Set<Department> departments = employee.getDepartments();
            departments.remove(departmentRepository.findByDepartmentId(departmentId).orElseThrow(()-> {throw new IllegalStateException("Department with id " + departmentId + " does not exist");}));
            employee.setDepartments(departments);
            employeeRepository.save(employee);
        }
        catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

}
