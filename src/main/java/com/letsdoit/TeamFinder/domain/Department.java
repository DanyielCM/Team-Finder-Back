package com.letsdoit.TeamFinder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


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
    @ToString.Exclude
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

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentDescription='" + departmentDescription + '\'' +
                ", organizationId=" + organizationId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, departmentName, departmentDescription, organizationId);
    }
}
