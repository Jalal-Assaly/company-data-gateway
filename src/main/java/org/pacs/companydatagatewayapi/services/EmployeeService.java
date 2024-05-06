package org.pacs.companydatagatewayapi.services;

import org.pacs.companydatagatewayapi.entities.Employee;
import org.pacs.companydatagatewayapi.entities.TimeSchedule;
import org.pacs.companydatagatewayapi.models.EmployeeAttributesModel;
import org.pacs.companydatagatewayapi.models.EmployeeModel;
import org.pacs.companydatagatewayapi.models.EmployeePersonalInfoModel;
import org.pacs.companydatagatewayapi.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeModel> getAllEmployees() {
        List<EmployeeModel> employeeModels = new ArrayList<>();

        employeeRepository
                .findAll()
                .forEach(employee -> {
                            TimeSchedule timeSchedule = getTimeSchedule(employee.getTimeSchedule());
                            EmployeeModel employeeModel = new EmployeeModel();
                            BeanUtils.copyProperties(employee, employeeModel, "timeSchedule");
                            employeeModel.setTimeSchedule(timeSchedule);
                            employeeModels.add(employeeModel);
                        }
                );

        return employeeModels;
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

        TimeSchedule timeSchedule = getTimeSchedule(emp.getTimeSchedule());

        return new EmployeeAttributesModel(
                emp.getId(), emp.getRole(), emp.getDepartment(), timeSchedule,
                emp.getClearanceLevel(), emp.getEmploymentStatus());
    }

    private TimeSchedule getTimeSchedule(Map<String, Object> timeScheduleMap) {

        Object timeScheduleObject = timeScheduleMap.get("daysOfWeek");
        List<String> daysOfWeekList = parseStringDaysToList(timeScheduleObject.toString());

        return new TimeSchedule(
                timeScheduleMap.get("startTime"),
                timeScheduleMap.get("endTime"),
                daysOfWeekList
        );
    }

    private List<String> parseStringDaysToList(String input) {
        List<String> list = new ArrayList<>();
        String[] elements = input.substring(1, input.length() - 1).split(", ");
        for (String element : elements) {
            list.add(element.trim().substring(0,3));
        }
        return list;
    }
}
