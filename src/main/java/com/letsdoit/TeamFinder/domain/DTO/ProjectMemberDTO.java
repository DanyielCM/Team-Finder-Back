package com.letsdoit.TeamFinder.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberDTO {
    private Integer employeeID;
    private String employeeName;
    private Set<String> employeeSkills;


    public ProjectMemberDTO(Integer employeeID, String employeeName) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeSkills = new HashSet<>();
    }
}
