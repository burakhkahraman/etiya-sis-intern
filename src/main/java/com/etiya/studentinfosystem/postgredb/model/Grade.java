package com.etiya.studentinfosystem.postgredb.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Benzersiz tanımlayıcı")
    private Long id;

    @OneToMany/*(mappedBy = "grade", cascade = CascadeType.ALL)*/
    @JsonIgnore
    private List<TakenCourse> takenCourses;

    @Schema(description = "Harf notu")
    private String gradeLetter;
    @Schema(description = "Minimum puan")
    private int minScore;
    @Schema(description = "Maksimum puan")
    private int maxScore;
    @Schema(description = "Aktiflik durumu")
    private int isActive;
    @Schema(description = "Notun kısa kodu")
    private String shortCode;
}
