package com.employees.management.ksquare.timesheet.repository;

import com.employees.management.ksquare.timesheet.entity.TimesheetProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TimesheetProjectRepository extends JpaRepository<TimesheetProject, UUID> {
}
