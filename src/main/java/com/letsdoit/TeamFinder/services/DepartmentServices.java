package com.letsdoit.TeamFinder.services;

import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.repositories.DepartmentRepository;
import com.letsdoit.TeamFinder.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServices {
    // This class is a service that will be used to interact with the database
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public DepartmentServices(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
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

}
