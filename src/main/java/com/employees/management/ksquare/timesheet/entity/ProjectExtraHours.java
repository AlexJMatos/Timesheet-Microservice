package com.employees.management.ksquare.timesheet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "project_extra_hours")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProjectExtraHours {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "project_hours_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "monday")
    private double mondayHours;

    @Column(name = "tuesday")
    private double tuesdayHours;

    @Column(name = "wednesday")
    private double wednesdayHours;

    @Column(name = "thursday")
    private double thursdayHours;

    @Column(name = "friday")
    private double fridayHours;

    @Column(name = "saturday")
    private double saturdayHours;

    @Column(name = "sunday")
    private double sundayHours;

    public double getTotalHours(){
        return mondayHours + tuesdayHours +
                wednesdayHours + thursdayHours +
                fridayHours + saturdayHours +
                sundayHours;
    }
}
