package com.letsdoit.TeamFinder.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private Integer employeeId;
    private String employeeUserName;
    private String employeeEmail;
    private Integer organizationId;
    private Integer projecthours;
}
