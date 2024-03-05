package com.letsdoit.TeamFinder.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationDTO {
    private String employeeUserName;
    private String employeeEmail;
    private String employeePassword;
    private String organizationName;
    private String hqAddress;

}
