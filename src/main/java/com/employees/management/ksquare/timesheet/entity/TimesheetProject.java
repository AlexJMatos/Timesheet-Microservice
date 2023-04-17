package com.employees.management.ksquare.timesheet.entity;

import com.employees.management.ksquare.timesheet.entity.enumerators.TimesheetProjectStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "timesheet_projects")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimesheetProject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "timesheet_id", referencedColumnName = "id")
//    @ToString.Exclude
//    @JsonIgnore
//    private Timesheet timesheet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ToString.Exclude
    private Project project;

    @Column(name = "timesheet_project_status")
    private TimesheetProjectStatus status;

    @Column(name = "project_hours")
    private double projectHours;

    @Column(name = "requester_comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "approves_id", referencedColumnName = "id")
    @ToString.Exclude
    private Employee approves;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "timesheet_project_hours",
            joinColumns = {@JoinColumn(name = "timesheet_project_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "project_hours_id", referencedColumnName = "id")})
    private ProjectHours hours;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "timesheet_project_extra_hours",
            joinColumns = {@JoinColumn(name = "timesheet_project_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "project_extra_hours_id", referencedColumnName = "id")})
    private ProjectExtraHours extraHours;
}
