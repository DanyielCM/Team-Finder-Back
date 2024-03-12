package com.letsdoit.TeamFinder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// This class is used to create a table in the database
@Entity
@Table(name = "departaments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departament_id_seq")
    private Long departmentId;
    private String departmentName;
    private String departmentDescription;
    @JoinColumn(name = "employee_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Employees departmentManager;
    @JoinColumn(name = "organization_id")
    @ManyToOne
    @JsonIgnore
    private Organization organizationId;

    public Department(String departmentName, String description, Integer managerId, Integer organizationID) {
        this.departmentName = departmentName;
        this.departmentDescription = description;
        this.departmentManager = new Employees();
        departmentManager.setEmployeeId(managerId);
        this.organizationId = new Organization();
        organizationId.setOrganizationId(organizationID);
    }

}
