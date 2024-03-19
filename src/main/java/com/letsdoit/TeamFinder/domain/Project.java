package com.letsdoit.TeamFinder.domain;

import com.letsdoit.TeamFinder.domain.Skills.EmployeeSkills;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
@Table(name = "projects")
@Entity
public class Project {
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer projectID;
    @Column(unique = true)
    private String name;
    private String status;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    private String projectPeriod;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employees projectManager;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_tech_stack",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<EmployeeSkills> employeeSkills;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_employees",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employees> employees;



    public Project(String name, String status, Date startDate, Date endDate,String projectPeriod, Employees projectManager) {
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectPeriod = projectPeriod;
        this.projectManager = projectManager;
    }
    public Project(String name, String status, Date startDate, Date endDate) {
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Project() {

    }
}