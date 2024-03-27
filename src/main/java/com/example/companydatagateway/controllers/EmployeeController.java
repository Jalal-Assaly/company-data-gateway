package com.example.companydatagateway.controllers;

import com.example.companydatagateway.entities.Employee;
import com.example.companydatagateway.models.EmployeeAttributesModel;
import com.example.companydatagateway.models.EmployeePersonalInfoModel;
import com.example.companydatagateway.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}/info")
    public ResponseEntity<EmployeePersonalInfoModel> getEmployeePersonalInfo(@PathVariable String id) {
        EmployeePersonalInfoModel employeePersonalInfoModel = employeeService.getEmployeePersonalInfo(id);
        return new ResponseEntity<>(employeePersonalInfoModel, HttpStatus.OK);
    }

    @GetMapping("/find/{id}/attributes")
    public ResponseEntity<EmployeeAttributesModel> getEmployeeAttributes(@PathVariable String id) {
        EmployeeAttributesModel employeeAttributesModel = employeeService.getEmployeeAttributes(id);
        return new ResponseEntity<>(employeeAttributesModel, HttpStatus.OK);
    }
}
