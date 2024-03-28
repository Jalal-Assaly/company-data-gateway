package org.pacs.companydatagatewayapi.services;

import org.pacs.companydatagatewayapi.entities.Employee;
import org.pacs.companydatagatewayapi.models.EmployeeAttributesModel;
import org.pacs.companydatagatewayapi.models.EmployeePersonalInfoModel;
import org.pacs.companydatagatewayapi.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeePersonalInfoModel getEmployeePersonalInfo(String email) {
        Employee emp = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Employee Email does not exist in Company Database"));
        return new EmployeePersonalInfoModel(
                emp.getId(), emp.getSsn(), emp.getFirstName(), emp.getLastName(), emp.getEmail());
    }

    public EmployeeAttributesModel getEmployeeAttributes(String email) {
        Employee emp = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Employee Email does not exist in Company Database"));

        // Adjust attributes to be compatible with PACS
        int yearsOfExperience = LocalDate.now().getYear() - emp.getContractStart().getYear() + 1;

        return new EmployeeAttributesModel(
                emp.getId(), emp.getRole(), emp.getDepartment(), emp.getTimeSchedule(),
                emp.getClearanceLevel(), emp.getEmploymentStatus(), yearsOfExperience);
    }
}