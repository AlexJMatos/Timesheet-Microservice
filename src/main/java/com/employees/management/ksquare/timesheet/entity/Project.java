package com.employees.management.ksquare.timesheet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    @Nullable
    private LocalDate endDate;

    @Column(name = "active")
    private boolean active;

    @Column(name = "project_owner")
    private String projectOwner;

    @ManyToOne
    @JoinColumn(name = "project_manager_id", referencedColumnName = "id")
    private Employee pmo;

    @Column(name = "cost_usd")
    private BigDecimal costUSD;
}
