package com.etiya.studentinfosystem.postgredb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
@Schema(description = "Ders bilgilerini temsil eder")

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private String description;
    private int creditValue;

    @ManyToOne
    @JoinColumn(name = "prerequisite_course_id")
    @JsonBackReference
    @Schema(description = "Dersin ön koşulunu temsil eder")
    private Course prerequisiteCourse;

    private int isActive;
}
