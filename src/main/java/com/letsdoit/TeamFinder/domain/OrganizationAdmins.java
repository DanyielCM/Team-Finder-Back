package com.letsdoit.TeamFinder.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Table(name = "organization_admins")
@Entity
public class OrganizationAdmins {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Getter
    private String organizationAdminName;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public OrganizationAdmins(Integer id, String name, Organization organization) {
        this.id = id;
        this.organizationAdminName = name;
        this.organization = organization;
    }

    public OrganizationAdmins() {

    }

}
