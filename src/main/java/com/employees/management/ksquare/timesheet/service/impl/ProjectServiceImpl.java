package com.employees.management.ksquare.timesheet.service.impl;

import com.employees.management.ksquare.timesheet.entity.Project;
import com.employees.management.ksquare.timesheet.exception.EntityNotFoundException;
import com.employees.management.ksquare.timesheet.repository.ProjectRepository;
import com.employees.management.ksquare.timesheet.service.ProjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository repository;

    @Override
    public Project findProjectByUUID(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> {
            log.error("Project not found - UUID: {}", uuid);
            throw new EntityNotFoundException(String.format("Project not found - UUID: %s", uuid));
        });
    }

    @Override
    public boolean projectExistsByUUID(UUID uuid) {
        return repository.existsById(uuid);
    }

    @Override
    public List<Project> findProjectsByUUID(List<UUID> uuidList) {
        return repository.findAllById(uuidList);
    }
}
