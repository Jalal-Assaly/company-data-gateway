package org.pacs.companydatagatewayapi.repositories;

import org.pacs.companydatagatewayapi.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByEmail(String email);
}
