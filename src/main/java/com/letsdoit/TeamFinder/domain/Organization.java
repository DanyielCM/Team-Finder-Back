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


// This class is used to create a table in the database
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "organization_id")
    private Integer organizationId;
    @Column(unique = true)
    private String organizationName;
    private String hqAddress;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name="organization_admin_junction",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @JsonIgnore
    private Employees orgAdminId;
    private String EmployeeRegisterURL;
    private String logo;


public Employees setOrgAdminId(Employees orgAdminId)
{
    this.orgAdminId = orgAdminId;
    return orgAdminId;
}


    public Organization(String organizationName, String hqAddress, Employees orgAdminId)
    {
        this.organizationName = organizationName;
        this.hqAddress = hqAddress;
        this.orgAdminId = orgAdminId;
        this.EmployeeRegisterURL = "http://localhost:8080/employee/register?organizationUserName=" + organizationName.replaceAll(" ","");
    }


}
