package com.etiya.studentinfosystem.postgredb.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @Column(name = "teacher_id", nullable = false)
    @Schema(description = "Bu dersi veren öğretmenin ID'si")
    private Long teacherId;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(nullable = true) // Eğer bazı derslerin açıklaması olmayabilirse nullable'ı true olarak bırakabilirsiniz.
    private String description;

    @Column(name = "credit_value", nullable = false)
    private int creditValue;

    @ManyToOne
    @JoinColumn(name = "prerequisite_course_id")
    private Course prerequisiteCourse;


    @Column(name = "is_active", nullable = false)
    private int isActive;

    @Column(name = "short_code", unique = true, nullable = false)
    @Schema(description = "Dersin kısa kodu")
    private String shortCode;
}
