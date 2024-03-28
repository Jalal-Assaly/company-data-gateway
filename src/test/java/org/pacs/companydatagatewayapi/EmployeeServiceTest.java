package org.pacs.companydatagatewayapi;

import org.pacs.companydatagatewayapi.entities.Employee;
import org.pacs.companydatagatewayapi.models.EmployeeAttributesModel;
import org.pacs.companydatagatewayapi.models.EmployeePersonalInfoModel;
import org.pacs.companydatagatewayapi.repositories.EmployeeRepository;
import org.pacs.companydatagatewayapi.services.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;

    Employee testEmployee1;
    EmployeeAttributesModel testEmployeeAttributesModel1;
    EmployeePersonalInfoModel testEmployeePersonalInfoModel1;
    Employee testEmployee2;
    EmployeeAttributesModel testEmployeeAttributesModel2;
    EmployeePersonalInfoModel testEmployeePersonalInfoModel2;

    @BeforeEach
    void init() {
        Map<String, Object> timeSchedule1 = Map.of(
                "startTime", LocalTime.of(9, 0),
                "endTime", LocalTime.of(17, 0),
                "daysOfWeek", new HashSet<>(Arrays.asList("Monday", "Tuesday", "Thursday", "Friday")));

        Map<String, Object> timeSchedule2 = Map.of(
                "startTime", LocalTime.of(13, 0),
                "endTime", LocalTime.of(21, 0),
                "daysOfWeek", new HashSet<>(Arrays.asList("Monday", "Wednesday", "Friday")));

        testEmployee1 = new Employee(
                "3",
                "555-55-5555",
                "Michael",
                "Johnson",
                "michael.johnson@example.com",
                "Coordinator",
                "HR",
                timeSchedule1,
                "Level 3",
                "Full-time",
                LocalDate.of(2012,3, 3));

        testEmployeePersonalInfoModel1 = new EmployeePersonalInfoModel(
                "3",
                "555-55-5555",
                "Michael",
                "Johnson",
                "michael.johnson@example.com"
        );

        testEmployeeAttributesModel1 = new EmployeeAttributesModel(
                "3",
                "Coordinator",
                "HR",
                timeSchedule1,
                "Level 3",
                "Full-time",
                10
        );

        testEmployee2 = new Employee(
                "4",
                "333-33-3333",
                "Emily",
                "Brown",
                "emily.brown@example.com",
                "Analyst",
                "Finance",
                timeSchedule2,
                "Level 2",
                "Full-time",
                LocalDate.of(2005,3, 4));

        testEmployeePersonalInfoModel2 = new EmployeePersonalInfoModel(
                "4",
                "333-33-3333",
                "Emily",
                "Brown",
                "emily.brown@example.com"
        );

        testEmployeeAttributesModel2 = new EmployeeAttributesModel(
                "4",
                "Analyst",
                "Finance",
                timeSchedule2,
                "Level 2",
                "Full-time",
                20
        );
    }

    @Test void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(testEmployee1, testEmployee2));

        List<Employee> actualEmployees = employeeService.getAllEmployees();

        verify(employeeRepository).findAll();
        assertThat(actualEmployees).hasSize(2);
        assertThat(actualEmployees.get(0)).usingRecursiveComparison().isEqualTo(testEmployee1);
        assertThat(actualEmployees.get(1)).usingRecursiveComparison().isEqualTo(testEmployee2);
    }

    @Test void getEmployeePersonalInfo_Success() {
        when(employeeRepository.findByEmail(testEmployee1.getEmail())).thenReturn(Optional.of(testEmployee1));

        EmployeePersonalInfoModel actualEmployeePersonalInfoModel =
                employeeService.getEmployeePersonalInfo(testEmployee1.getEmail());

        verify(employeeRepository).findByEmail(any(String.class));
        assertThat(actualEmployeePersonalInfoModel).usingRecursiveComparison().isEqualTo(testEmployeePersonalInfoModel1);
    }

    @Test void getEmployeePersonalInfo_NotFound() {
        when(employeeRepository.findByEmail("xyz")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeePersonalInfo("xyz"))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Employee Email does not exist in Company Database");
    }

    @Test void getEmployeeAttributes_Success() {
        when(employeeRepository.findByEmail(testEmployee2.getEmail())).thenReturn(Optional.of(testEmployee2));

        EmployeeAttributesModel actualEmployeeAttributesModel =
                employeeService.getEmployeeAttributes(testEmployee2.getEmail());

        verify(employeeRepository).findByEmail(any(String.class));
        assertThat(actualEmployeeAttributesModel).usingRecursiveComparison().isEqualTo(testEmployeeAttributesModel2);
    }

    @Test void getEmployeeAttributes_NotFound() {
        when(employeeRepository.findByEmail("xyz")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeAttributes("xyz"))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Employee Email does not exist in Company Database");
    }
}
