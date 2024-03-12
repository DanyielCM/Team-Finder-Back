package com.letsdoit.TeamFinder.services;

import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServices {
    // This class is a service that will be used to interact with the database
    private final DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentServices(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
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
}
