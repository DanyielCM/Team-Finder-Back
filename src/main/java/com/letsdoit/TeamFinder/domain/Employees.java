package com.letsdoit.TeamFinder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employees implements UserDetails {
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer employeeId;
    private String employeeUserName;
    @Column(unique = true)
    private String employeeEmail;
    private String employeePassword;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    @JsonIgnore
    private Organization organization;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="emplyees_roles_junction",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities;
    private Integer projecthours;
    @OneToOne
    @MapsId
    @JoinColumn(name = "employee_id")
    private ProjectManagers projectManager;

    public Employees(String employeeUserName, String employeePassword, String employeeEmail, Set<Role> authorities) {
        this.employeeUserName = employeeUserName;
        this.employeeEmail = employeeEmail;
        this.employeePassword = employeePassword;
        this.authorities = authorities;
    }

    public Employees(String employeeUserName, String employeePassword, String employeeEmail, Integer orgId, Set<Role> authorities) {
        this.employeeUserName = employeeUserName;
        this.employeeEmail = employeeEmail;
        this.employeePassword = employeePassword;
        this.organization = new Organization();
        organization.setOrganizationId(orgId);
        this.authorities = authorities;
        this.projecthours=0;
    }

    public Employees(String email) {
        this.employeeEmail = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.employeePassword;
    }

    @Override
    public String getUsername() {
        return this.employeeUserName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
