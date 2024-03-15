package com.letsdoit.TeamFinder.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "project_managers")
@Entity
public class ProjectManagers {
    @Column(name = "project_manager_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer projectManagerID;
    private String name;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "name")
    private Project project;
    @OneToOne(mappedBy = "project_manager_id")
    @PrimaryKeyJoinColumn
    private Employees employee;

    public ProjectManagers(Integer projectManagerID, String name, Project project, Employees employee) {
        this.projectManagerID = projectManagerID;
        this.name = name;
        this.project = project;
        this.employee = employee;
    }

    public ProjectManagers() {

    }
}
