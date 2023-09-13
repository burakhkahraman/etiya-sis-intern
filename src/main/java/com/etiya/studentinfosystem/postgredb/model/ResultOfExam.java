package com.etiya.studentinfosystem.postgredb.model;

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
@Table(name = "result_of_exam")
@Schema(description = "Sınav sonuçlarının detay bilgileri için model")
public class ResultOfExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Sınav sonucunun benzersiz tanımlayıcısı")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_type_id", referencedColumnName = "exam_type_id")
    @JsonManagedReference
    private ExamType examType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taken_course_id", referencedColumnName = "id")
    @JsonManagedReference
    private TakenCourse takenCourse;

    @Schema(description = "Sınavdan alınan puan")
    private Long score;

    @Schema(description = "Sınavın aktif olup olmadığı")
    private int isActive;

 /*   @Column(name = "short_code")
    @Schema(description = "Sınavın türünün kısa kodu")
    private String shortCode;*/
}
