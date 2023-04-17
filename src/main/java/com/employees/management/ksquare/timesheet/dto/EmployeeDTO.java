package com.employees.management.ksquare.timesheet.dto;

import com.employees.management.ksquare.timesheet.entity.Address;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeeDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String jobEmail;
    private String position;
    private String country;
    private String photoUrl;
}
