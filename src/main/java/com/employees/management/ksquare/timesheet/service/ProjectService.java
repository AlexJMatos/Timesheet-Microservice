package com.employees.management.ksquare.timesheet.service;

import com.employees.management.ksquare.timesheet.entity.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    Project findProjectByUUID(UUID uuid);

    boolean projectExistsByUUID(UUID uuid);

    List<Project> findProjectsByUUID(List<UUID> uuidList);
}
