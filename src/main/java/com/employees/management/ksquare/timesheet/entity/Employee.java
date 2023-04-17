package com.employees.management.ksquare.timesheet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "job_email")
    private String jobEmail;
    @Column(name = "phone")
    private String phone;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Address address;
    @Column(name = "gender")
    private String gender;
    @Column(name = "hiring_date")
    private LocalDate hiringDate;
    @Column(name = "employee_position")
    private String position;
    @Column(name = "country")
    private String country;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "active")
    private boolean active;
}
