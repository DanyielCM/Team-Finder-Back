package com.letsdoit.TeamFinder.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationEmployeeDTO {
    private String employeeUserName;
    private String employeeEmail;
    private String employeePassword;

}
