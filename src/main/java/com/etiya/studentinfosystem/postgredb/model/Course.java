package com.etiya.studentinfosystem.postgredb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<TakenCourse> takenCourses;

    private String courseName;
    private String description;
    private int creditValue;

    @ManyToOne
    @JoinColumn(name = "prerequisite_course_id")
    @JsonBackReference
    @Schema(description = "Dersin ön koşulunu temsil eder")
    private Course prerequisiteCourse;

    private int isActive;

    @Schema(description = "Dersin kısa kodu")
    @Column(unique = true)  // Eğer shortCode'un benzersiz olmasını istiyorsanız bu anotasyonu ekleyin.
    private String shortCode;
}
