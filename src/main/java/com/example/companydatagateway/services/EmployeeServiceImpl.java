package com.example.companydatagateway.services;

import com.example.companydatagateway.entities.Employee;
import com.example.companydatagateway.models.EmployeeAttributesModel;
import com.example.companydatagateway.models.EmployeePersonalInfoModel;
import com.example.companydatagateway.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public EmployeePersonalInfoModel getEmployeePersonalInfo(String id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee ID does not exist in Company Database"));

        return new EmployeePersonalInfoModel(
                emp.getId(), emp.getSsn(), emp.getFirstName(), emp.getLastName(), emp.getEmail());
    }

    @Override
    public EmployeeAttributesModel getEmployeeAttributes(String id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee ID does not exist in Company Database"));

        // Adjust attributes to be compatible with PACS
        int yearsOfExperience = LocalDate.now().getYear() - emp.getContractStart().getYear() + 1;

        return new EmployeeAttributesModel(
                emp.getId(), emp.getRole(), emp.getDepartment(), emp.getTimeSchedule(),
                emp.getClearanceLevel(), emp.getEmploymentStatus(), yearsOfExperience);
    }
}
