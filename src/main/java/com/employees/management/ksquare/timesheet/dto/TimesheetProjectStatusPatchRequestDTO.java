package com.employees.management.ksquare.timesheet.dto;

import com.employees.management.ksquare.timesheet.entity.enumerators.TimesheetProjectStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.employees.management.ksquare.timesheet.validation.TimesheetProjectStatusType;
import com.employees.management.ksquare.timesheet.validation.ValueOfEnum;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimesheetProjectStatusPatchRequestDTO {
    @NotNull(message = "The project id cannot be empty")
    private UUID timesheetProjectId;

    @NotNull(message = "The status must be (Approved|Rejected)")
    @ValueOfEnum(enumClass = TimesheetProjectStatus.class, message = "Invalid type, accepted values: (Pending|Rejected|Approved|Draft)")
    @TimesheetProjectStatusType(anyOf = {TimesheetProjectStatus.Approved, TimesheetProjectStatus.Rejected}, message = "Status accepted values (Approved|Rejected)")
    private String status;

    private String comment;
}
