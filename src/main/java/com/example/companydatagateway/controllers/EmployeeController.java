package com.example.companydatagateway.controllers;

import com.example.companydatagateway.entities.Employee;
import com.example.companydatagateway.models.EmployeeAttributesDTO;
import com.example.companydatagateway.models.EmployeePersonalInfoDTO;
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

    @GetMapping("/{id}/info")
    public ResponseEntity<EmployeePersonalInfoDTO> getEmployeePersonalInfo(@PathVariable String id) {
        EmployeePersonalInfoDTO employeePersonalInfoDTO = employeeService.getEmployeePersonalInfo(id);
        return new ResponseEntity<>(employeePersonalInfoDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}/attributes")
    public ResponseEntity<EmployeeAttributesDTO> getEmployeeAttributes(@PathVariable String id) {
        EmployeeAttributesDTO employeeAttributesDTO = employeeService.getEmployeeAttributes(id);
        return new ResponseEntity<>(employeeAttributesDTO, HttpStatus.OK);
    }
}
