package org.pacs.companydatagatewayapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeePersonalInfoModel {
    private String id;
    private String ssn;
    private String firstName;
    private String lastName;
    private String email;
}
