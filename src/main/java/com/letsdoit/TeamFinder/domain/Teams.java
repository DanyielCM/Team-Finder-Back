package com.letsdoit.TeamFinder.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "teams")
@Data
public class Teams {
    @Column(name = "teams_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer teamsId;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "team_leader")
    private String teamLeader;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;

    public Teams(Integer teamsId, String teamName, String teamLeader, Project project) {
        this.teamsId = teamsId;
        this.teamName = teamName;
        this.teamLeader = teamLeader;
        this.project = project;
    }

    public Teams() {

    }
}
