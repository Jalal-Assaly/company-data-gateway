package com.example.companydatagateway.services;

import com.example.companydatagateway.entities.Employee;
import com.example.companydatagateway.models.EmployeeAttributesDTO;
import com.example.companydatagateway.models.EmployeePersonalInfoDTO;

import java.util.List;

public interface EmployeeService {

     List<Employee> getAllEmployees();
     EmployeePersonalInfoDTO getEmployeePersonalInfo(String id);
     EmployeeAttributesDTO getEmployeeAttributes(String id);
}
