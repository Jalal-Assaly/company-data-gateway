package com.example.companydatagateway.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@AllArgsConstructor
public class EmployeeAttributesDTO {
    private String id;
    private String role;
    private String department;
    private Map<String, Object> timeSchedule;
    private String clearanceLevel;
    private String employmentStatus;
    private Integer yearsOfExperience;
}
