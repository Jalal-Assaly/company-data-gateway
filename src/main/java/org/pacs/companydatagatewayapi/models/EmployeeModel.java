package org.pacs.companydatagatewayapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pacs.companydatagatewayapi.entities.TimeSchedule;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {

    @JsonProperty("ID")
    private String id;
    @JsonProperty("SSN")
    private String ssn;
    @JsonProperty("FN")
    private String firstName;
    @JsonProperty("LN")
    private String lastName;
    @JsonProperty("EM")
    private String email;

    @JsonProperty("RL")
    private String role;
    @JsonProperty("DP")
    private String department;
    @JsonProperty("TS")
    private TimeSchedule timeSchedule;
    @JsonProperty("CL")
    private String clearanceLevel;
    @JsonProperty("ES")
    private String employmentStatus;
    @JsonProperty("CS")
    private LocalDate contractStart;
}
