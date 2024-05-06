package org.pacs.companydatagatewayapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TimeSchedule implements Serializable {
    @JsonProperty("ST")
    private Object startTime;
    @JsonProperty("ET")
    private Object endTime;
    @JsonProperty("DW")
    private Object daysOfWeek;
}
