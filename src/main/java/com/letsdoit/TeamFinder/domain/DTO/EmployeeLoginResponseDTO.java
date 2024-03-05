package com.letsdoit.TeamFinder.domain.DTO;

import com.letsdoit.TeamFinder.domain.Employees;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLoginResponseDTO {
    private Employees employee;
    private String jwt;
}
