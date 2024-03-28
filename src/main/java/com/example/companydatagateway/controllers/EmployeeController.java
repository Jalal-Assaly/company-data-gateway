package com.example.companydatagateway.controllers;

import com.example.companydatagateway.entities.Employee;
import com.example.companydatagateway.models.EmployeeAttributesModel;
import com.example.companydatagateway.models.EmployeePersonalInfoModel;
import com.example.companydatagateway.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/find/info/email")
    public ResponseEntity<EmployeePersonalInfoModel> getEmployeePersonalInfo(@RequestParam String email) {
        EmployeePersonalInfoModel employeePersonalInfoModel = employeeService.getEmployeePersonalInfo(email);
        return new ResponseEntity<>(employeePersonalInfoModel, HttpStatus.OK);
    }

    @GetMapping("/find/attributes/email")
    public ResponseEntity<EmployeeAttributesModel> getEmployeeAttributes(@RequestParam String email) {
        EmployeeAttributesModel employeeAttributesModel = employeeService.getEmployeeAttributes(email);
        return new ResponseEntity<>(employeeAttributesModel, HttpStatus.OK);
    }
}
