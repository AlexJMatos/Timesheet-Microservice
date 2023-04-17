package com.employees.management.ksquare.timesheet.repository;

import com.employees.management.ksquare.timesheet.entity.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, UUID> {
    @Query(value = "SELECT * FROM timesheets WHERE requester_id = :employeeId", nativeQuery = true)
    List<Timesheet> findTimesheetByEmployeeUUID(@Param("employeeId") UUID uuid);
}
