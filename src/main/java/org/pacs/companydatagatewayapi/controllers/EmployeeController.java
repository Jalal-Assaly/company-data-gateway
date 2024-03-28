package org.pacs.companydatagatewayapi.controllers;

import org.pacs.companydatagatewayapi.entities.Employee;
import org.pacs.companydatagatewayapi.models.EmployeeAttributesModel;
import org.pacs.companydatagatewayapi.models.EmployeePersonalInfoModel;
import org.pacs.companydatagatewayapi.services.EmployeeService;
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
