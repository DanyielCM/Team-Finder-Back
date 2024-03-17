package com.letsdoit.TeamFinder;

import com.letsdoit.TeamFinder.Enums.Roles;
import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Organization;
import com.letsdoit.TeamFinder.domain.Role;
import com.letsdoit.TeamFinder.domain.Skills.SkillCategory;
import com.letsdoit.TeamFinder.repositories.EmployeeRepository;
import com.letsdoit.TeamFinder.repositories.OrganizationRepository;
import com.letsdoit.TeamFinder.repositories.RoleRepository;
import com.letsdoit.TeamFinder.repositories.Skill.SkillCategoryRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.oauth2.login.OAuth2LoginSecurityMarker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@Log
public class TeamFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamFinderApplication.class, args);
	}

	private final EmployeeRepository employeeRep;
	private final RoleRepository roleRepository;
	private final SkillCategoryRepository skillCategoryRepository;
	private final OrganizationRepository organizationRepository;
	public TeamFinderApplication(SkillCategoryRepository skillCategoryRepository, EmployeeRepository employeeRep, RoleRepository roleRepository, OrganizationRepository organizationRepository) {
		this.employeeRep = employeeRep;
		this.roleRepository = roleRepository;
		this.organizationRepository = organizationRepository;
		this.skillCategoryRepository = skillCategoryRepository;
	}

	@Bean
	CommandLineRunner run(SkillCategoryRepository skillCategoryRepository, RoleRepository roleRepository, OrganizationRepository organizationRepository, PasswordEncoder passwordEncoder) {
		return args -> {

			String uri = "https://zenquotes.io/api/today";
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);
			log.info("Quote of the day: " + result);

			/*Organization org = organizationRepository.findById(602).get();
			Set<Role> roles = roleRepository.findByAuthority("DepartmentManager").map(Set::of).get();
			List<Employees> employees = employeeRep.findAllByOrganizationAndAuthorities(org, roles);
			log.info("Employees: " + employees);*/

			SkillCategory skill = skillCategoryRepository.findBySkillCategoryName("Programming Language").orElseGet(() -> {
				SkillCategory skillCategory = new SkillCategory();
				skillCategory.setSkillCategoryName("Programming Language");
				return skillCategoryRepository.save(skillCategory);
			});

			SkillCategory skill2 = skillCategoryRepository.findBySkillCategoryName("Framework").orElseGet(() -> {
				SkillCategory skillCategory = new SkillCategory();
				skillCategory.setSkillCategoryName("Framework");
				return skillCategoryRepository.save(skillCategory);
			});

			SkillCategory skill3 = skillCategoryRepository.findBySkillCategoryName("Libraries").orElseGet(() -> {
				SkillCategory skillCategory = new SkillCategory();
				skillCategory.setSkillCategoryName("Libraries");
				return skillCategoryRepository.save(skillCategory);
			});

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