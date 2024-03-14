package com.letsdoit.TeamFinder.services;

import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.repositories.DepartmentRepository;
import com.letsdoit.TeamFinder.repositories.EmployeeRepository;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;
import com.letsdoit.TeamFinder.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
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

    public List<Employees> getUnassignedEmployees(Integer orgId){
        try{
            Organization organization = organizationRepository.findById(orgId).orElseThrow(()-> {throw new IllegalStateException("Organization with id " + orgId + " does not exist");});
            return employeeRepository.findAllByOrganizationAndDepartmentsEmptyAndAuthoritiesNotContaining(organization, roleRepository.findByAuthority("OrganizationAdmin").get());
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
