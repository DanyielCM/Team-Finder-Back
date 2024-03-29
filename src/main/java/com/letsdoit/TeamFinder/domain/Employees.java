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
import java.util.Objects;
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
    @JsonIgnore
    private String employeePassword;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    private Organization organization;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="emplyees_roles_junction",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities;
    private Integer projecthours;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="employees_department_junction",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> departments;


    @Override
    public String toString() {
        return "Employees{" +
                "employeeId=" + employeeId +
                ", employeeUserName='" + employeeUserName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePassword='" + employeePassword + '\'' +
                ", organization=" + organization +
                ", authorities=" + authorities +
                ", projecthours=" + projecthours +
                '}';
    }



    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeUserName, employeeEmail, employeePassword, organization, authorities, projecthours);
    }

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


    @JsonIgnore
    public Set<Department> getDepartments() {
        return this.departments;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.employeePassword;
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return this.employeeUserName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
