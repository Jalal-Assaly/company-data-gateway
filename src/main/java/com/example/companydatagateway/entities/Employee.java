package com.example.companydatagateway.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    private String id;
    private String ssn;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String department;

    @Convert(converter = TimeScheduleConverter.class)
    private Map<String, Object> timeSchedule;

    private String clearanceLevel;
    private String employmentStatus;
    private LocalDate contractStart;
}
