package com.example.companydatagateway.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeePersonalInfoModel {
    private String id;
    private String ssn;
    private String firstName;
    private String lastName;
    private String email;
}
