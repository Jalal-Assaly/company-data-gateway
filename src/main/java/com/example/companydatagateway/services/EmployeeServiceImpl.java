package com.example.companydatagateway.services;

import com.example.companydatagateway.entities.Employee;
import com.example.companydatagateway.models.EmployeeAttributesDTO;
import com.example.companydatagateway.models.EmployeePersonalInfoDTO;
import com.example.companydatagateway.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeePersonalInfoDTO getEmployeePersonalInfo(String id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee ID does not exist in Company Database"));

        return new EmployeePersonalInfoDTO(
                emp.getId(), emp.getSsn(), emp.getFirstName(), emp.getLastName(), emp.getEmail());
    }

    @Override
    public EmployeeAttributesDTO getEmployeeAttributes(String id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee ID does not exist in Company Database"));

        return new EmployeeAttributesDTO(
                emp.getId(), emp.getRole(), emp.getDepartment(), emp.getTimeSchedule(),
                emp.getClearanceLevel(), emp.getEmploymentStatus(), emp.getContractStart());
    }
}
