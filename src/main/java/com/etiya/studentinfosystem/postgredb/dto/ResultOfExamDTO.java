package com.etiya.studentinfosystem.postgredb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultOfExamDTO {
/*
    @Schema(description = "Sınav sonucunun benzersiz tanımlayıcısı")
    private Long id;

    @Schema(description = "Sınav tipi ID")
    @NotNull(message = "examTypeId cannot be null")
    private Long examTypeId;

    @Schema(description = "Alınan ders ID")
    @NotNull(message = "takenCourseId cannot be null")
    private Long takenCourseId;

    @Schema(description = "Sınavdan alınan puan")
    private Double score;

    @Schema(description = "Sınavın aktif olup olmadığı")
    private int isActive;
*/
/*private Long studentId;
    private String courseName;
    private String term;*/

/*    private String courseName; // Ders adı
    private String examTypeName;
    private Long gradeId;
    private int isActive;
    private Long examTypeId;
    private Long takenCourseId;
    private Double score;*/


    private String examTypeName;
    private Double score;


}
