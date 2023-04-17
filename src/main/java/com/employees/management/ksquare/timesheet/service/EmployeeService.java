package com.employees.management.ksquare.timesheet.service;

import com.employees.management.ksquare.timesheet.entity.Employee;
import com.employees.management.ksquare.timesheet.exception.EntityNotFoundException;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {
    Employee findEmployeeByUUID(UUID uuid) throws EntityNotFoundException;
    Boolean employeeExistsAndIsActive(UUID uuid);
}
