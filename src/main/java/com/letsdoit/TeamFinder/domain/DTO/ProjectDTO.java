package com.letsdoit.TeamFinder.domain.DTO;

import com.letsdoit.TeamFinder.domain.Employees;
import com.letsdoit.TeamFinder.domain.Skills.EmployeeSkills;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Integer projectID;
    private String name;
    private String status;
    private Date startDate;
    private Date endDate;
    private String projectPeriod;
    private Integer projectManager;

}
