package com.example.companydatagateway.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.HashMap;
import java.util.Map;

public class TimeScheduleConverter implements AttributeConverter<Map<String, Object>, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> timeSchedule) {
        try {
            return objectMapper.writeValueAsString(timeSchedule);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException("JSON attribute was not converted to Database Column");
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String timeScheduleJSON) {
        try {
            return objectMapper.readValue(timeScheduleJSON, new TypeReference<HashMap<String, Object>>() {});
        } catch (JsonProcessingException exception) {
            throw new RuntimeException("JSON attribute was not correctly parsed as a Java Object");
        }
    }
}