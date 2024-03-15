package com.letsdoit.TeamFinder.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Table(name = "projects")
@Entity
public class Project {
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer projectID;
    private String name;
    private String status;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_manager_id", referencedColumnName = "project_manager_id")
    private ProjectManagers projectManager;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Teams team;

    public Project(Integer projectID, String name, String status, Date startDate, Date endDate, ProjectManagers projectManager, Teams team) {
        this.projectID = projectID;
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectManager = projectManager;
        this.team = team;
    }

    public Project() {

    }
}
