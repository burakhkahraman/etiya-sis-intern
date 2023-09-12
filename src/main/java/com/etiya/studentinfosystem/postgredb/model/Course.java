package com.etiya.studentinfosystem.postgredb.model;


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

    @Column(nullable = true)
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

    @Column(name = "is_mandatory", nullable = false)
    @Schema(description = "Dersin zorunlu olup olmadığı bilgisi")
    private Boolean isMandatory;  // Zorunlu mu seçmeli mi olduğunu belirten alan

}