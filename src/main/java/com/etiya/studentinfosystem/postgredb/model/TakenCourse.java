package com.etiya.studentinfosystem.postgredb.model;

import com.etiya.studentinfosystem.postgredb.dto.TakenCourseDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "taken_courses")
@Schema(description = "Alınan derslerin detay bilgileri için model")
public class TakenCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Alınan dersin benzersiz tanımlayıcısı")
    private Long id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Schema(description = "Dönem bilgisi")
    private String term;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @Schema(description = "Kaydın aktiflik durumu")
    private int isActive;

    @Schema(description = "Dersin kısa kodu")
    private String shortCode;
}
