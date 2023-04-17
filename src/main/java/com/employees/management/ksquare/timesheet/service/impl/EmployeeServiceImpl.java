package com.employees.management.ksquare.timesheet.service.impl;

import com.employees.management.ksquare.timesheet.entity.Employee;
import com.employees.management.ksquare.timesheet.exception.EntityNotFoundException;
import com.employees.management.ksquare.timesheet.repository.EmployeeRepository;
import com.employees.management.ksquare.timesheet.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee findEmployeeByUUID(UUID uuid) throws EntityNotFoundException {
        return repository.findById(uuid).
                orElseThrow(() -> {
                    log.error("Employee not found - UUID: {}", uuid);
                    throw new EntityNotFoundException(String.format("Employee not found - UUID: %s", uuid));
                });
    }

    @Override
    public Boolean employeeExistsAndIsActive(UUID uuid) {
        return repository.findById(uuid).
                map(Employee::isActive).
                orElseThrow(() -> {
                    log.error("Employee not found - UUID: {}", uuid);
                    throw new EntityNotFoundException(String.format("Employee not found - UUID: %s", uuid));
                });
    }
}
