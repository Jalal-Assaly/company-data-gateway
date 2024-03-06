package com.example.companydatagateway.repositories;

import com.example.companydatagateway.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
