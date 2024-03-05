package com.letsdoit.TeamFinder.services;

import com.letsdoit.TeamFinder.domain.Department;
import com.letsdoit.TeamFinder.repositories.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentServices {
    // This class is a service that will be used to interact with the database
    private final DepartamentRepository departamentRepository;
    @Autowired
    public DepartamentServices(DepartamentRepository departamentRepository) {
        this.departamentRepository = departamentRepository;
    }

    // This method will be used to create a new department
    public Department createDepartament(Department department) {
        return departamentRepository.save(department);
    }

    // This method will be used to return a department
    public Department getDepartament(Integer id) {
        return departamentRepository.findById(id).orElse(null);
    }

    // This method will be used to delete a department
    public void deleteDepartament(Integer id) {
        departamentRepository.deleteById(id);
    }
}
