package com.example.companydatagateway.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class TimeSchedule implements Serializable {

    @NotNull(message = "The start time can not be null")
    private LocalTime startTime;
    @NotNull(message = "The end time can not be null")
    private LocalTime endTime;
    @NotEmpty(message = "The week schedule can not be empty")
    private Set<String> daysOfWeek;
}
