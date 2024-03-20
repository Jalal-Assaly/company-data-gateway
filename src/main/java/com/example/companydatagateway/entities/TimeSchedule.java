package com.example.companydatagateway.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class TimeSchedule implements Serializable {
    private LocalTime startTime;
    private LocalTime endTime;
    private Set<String> daysOfWeek;
}
