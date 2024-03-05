package com.letsdoit.TeamFinder.services;


import com.letsdoit.TeamFinder.domain.DTO.EmployeeLoginResponseDTO;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.domain.Role;
import com.letsdoit.TeamFinder.repositories.EmployeeRepository;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;
import com.letsdoit.TeamFinder.repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@Log
@Transactional
public class AuthenticationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    public Organization registerOrganization(String organizationName,String hqAddress,Employees Id)
    {
            return organizationRepository.save(new Organization(organizationName, hqAddress, null));
    }


// register a new employee
    public Employees registerEmployee(String userName, String email, String password, Integer Id)
    {

        String encodedPassword = passwordEncoder.encode(password);
        Role employeeRole = roleRepository.findByAuthority("Employee").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(employeeRole);
        Organization organization = organizationRepository.findById(Id).orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        Employees employee = new Employees(userName, encodedPassword, email, Id, authorities);
        employee.setOrganization(organization);
        return employeeRepository.save(employee);
    }

    //Create a new organization
    public Employees registerEmployee(String username, String email, String password, String role)
    {

        String encodedPassword = passwordEncoder.encode(password);
        Role employeeRole = roleRepository.findByAuthority(role).get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(employeeRole);
        Employees employee = new Employees(username, encodedPassword, email, authorities);
        return employeeRepository.save(employee);
    }

    public EmployeeLoginResponseDTO loginEmployee(String email, String password) {
        try {

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            String token = tokenService.generateJwt(auth);
            return new EmployeeLoginResponseDTO(employeeRepository.findByEmployeeEmail(email).orElseThrow(()->
                    new EntityNotFoundException("User not Found")), token);

        }
        catch (AuthenticationException e)
        {
            log.info(e.getMessage());
            return new EmployeeLoginResponseDTO(null,"");
        }
    }

   /* public LoginResponseDTO login(String email, String password) {
        try {

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(employeeRepository.findByEmployeeEmail(email).get(), token);

        }
        catch (AuthenticationException e)
        {
            log.info(e.getMessage());
            return new LoginResponseDTO(null,"");
        }
    }*/

}

