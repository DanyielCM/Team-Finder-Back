package com.letsdoit.TeamFinder;

import com.letsdoit.TeamFinder.Enums.Roles;
import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.domain.Role;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;
import com.letsdoit.TeamFinder.repositories.RoleRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.oauth2.login.OAuth2LoginSecurityMarker;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@Log
public class TeamFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamFinderApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, OrganizationRepository organizationRepository, PasswordEncoder passwordEncoder) {
		return args -> {

			Role employeeRole = roleRepository.findByAuthority("Employee").orElseGet(() -> {
				Role role = new Role();
				role.setAuthority("Employee");
				return roleRepository.save(role);
			});

			Role organizationAdminRole = roleRepository.findByAuthority("OrganizationAdmin").orElseGet(() -> {
				Role role = new Role();
				role.setAuthority("OrganizationAdmin");
				return roleRepository.save(role);
			});

			Role departmentManagerRole = roleRepository.findByAuthority("DepartmentManager").orElseGet(() -> {
				Role role = new Role();
				role.setAuthority("DepartmentManager");
				return roleRepository.save(role);
			});

			Role projectManagerRole = roleRepository.findByAuthority("ProjectManager").orElseGet(() -> {
				Role role = new Role();
				role.setAuthority("ProjectManager");
				return roleRepository.save(role);
			});









		};

	}
}