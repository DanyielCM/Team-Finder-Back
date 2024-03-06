package com.letsdoit.TeamFinder.Controllers;

import com.letsdoit.TeamFinder.domain.DTO.*;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.repositories.EmployeeRepository;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;
import com.letsdoit.TeamFinder.services.AuthenticationService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@Log
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    //TODO: adauga restul de campuri din Organization
    @PostMapping("/register")
    public ResponseEntity<?> registerOrganization(@RequestBody RegistrationDTO registrationDTO) {
        try {
            Organization org = authenticationService.registerOrganization(registrationDTO.getOrganizationName(),
                    registrationDTO.getHqAddress(), null);
            Employees admin = authenticationService.registerEmployee(registrationDTO.getEmployeeUserName(), registrationDTO.getEmployeeEmail().toLowerCase(),
                    registrationDTO.getEmployeePassword(), "OrganizationAdmin");
            Employees adminId = employeeRepository.findByEmployeeEmail(registrationDTO.getEmployeeEmail().toLowerCase()).get();
            org.setOrgAdminId(adminId);
            org.setEmployeeRegisterURL("http://localhost:8080/auth/employee/register?orgId=" + org.getOrganizationId());
            admin.setOrganization(org);
            employeeRepository.save(admin);
            organizationRepository.save(org);

            return ResponseEntity.ok(org);
        } catch (DataIntegrityViolationException e) {
            log.info("User " + registrationDTO.getEmployeeUserName() + " already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // return httpStatus 409 Conflict
        }
    }


    /* @PostMapping("/login")
     public EmployeeLoginResponseDTO login(@RequestBody LoginEmployeeDTO loginDTO)  {

         return authenticationService.loginEmployee(loginDTO.getEmployeeEmail().toLowerCase(), loginDTO.getEmployeePassword());
     }*/
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginEmployeeDTO loginDTO) {
        if (employeeRepository.findByEmployeeEmail(loginDTO.getEmployeeEmail().toLowerCase()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        EmployeeLoginResponseDTO responseDTO = authenticationService.loginEmployee(loginDTO.getEmployeeEmail().toLowerCase(), loginDTO.getEmployeePassword());
        if (responseDTO.getEmployee() == null)
            return ResponseEntity.status(401).build();
        else return ResponseEntity.ok(responseDTO);
    }



   /* @PostMapping("/employee/login")
    public EmployeeLoginResponseDTO loginEmployee(@RequestBody LoginEmployeeDTO loginDTO)  {

        return authenticationService.loginEmployee(loginDTO.getEmployeeEmail().toLowerCase(), loginDTO.getEmployeePassword());
    }*/


    //TODO: modifica pentru a da response cu status code
    @PostMapping("/employee/register")
    public Employees registerEmployee(@RequestParam Integer orgId, @RequestBody RegistrationEmployeeDTO registerEmployeeDTO) {
        try {
            return authenticationService.registerEmployee(registerEmployeeDTO.getEmployeeUserName(), registerEmployeeDTO.getEmployeeEmail().toLowerCase(), registerEmployeeDTO.getEmployeePassword(), orgId);
        } catch (DataIntegrityViolationException e) {
            log.info("User " + registerEmployeeDTO.getEmployeeEmail() + " already exists");
            return null;
        }
    }

}
