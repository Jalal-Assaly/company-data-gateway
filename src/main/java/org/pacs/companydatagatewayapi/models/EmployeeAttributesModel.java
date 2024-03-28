package org.pacs.companydatagatewayapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class EmployeeAttributesModel {
    private String id;
    private String role;
    private String department;
    private Map<String, Object> timeSchedule;
    private String clearanceLevel;
    private String employmentStatus;
    private Integer yearsOfExperience;
}
