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
@Table(name = "absenteeisms")
public class Absenteeism {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Devamsızlığın benzersiz tanımlayıcısı")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taken_course_id", referencedColumnName = "id")
    @Schema(description = "Hangi derse ait olduğuna dair bilgi")
    private TakenCourse takenCourse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    @Schema(description = "Bu devamsızlık kaydına sahip öğrenci")
    private Student student;
    @Schema(description = "Devamsızlık tarihi")
    private String date;
    @Schema(description = "Devamsızlık süresi")
    private int duration;
    @Schema(description = "Kaydın aktif olup olmadığına dair durum bilgisi")
    private int isActive;
}
