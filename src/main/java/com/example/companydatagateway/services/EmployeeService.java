package com.example.companydatagateway.services;

import com.example.companydatagateway.entities.Employee;
import com.example.companydatagateway.models.EmployeeAttributesModel;
import com.example.companydatagateway.models.EmployeePersonalInfoModel;

import java.util.List;

public interface EmployeeService {

     List<Employee> getAllEmployees();
     EmployeePersonalInfoModel getEmployeePersonalInfo(String id);
     EmployeeAttributesModel getEmployeeAttributes(String id);
}
