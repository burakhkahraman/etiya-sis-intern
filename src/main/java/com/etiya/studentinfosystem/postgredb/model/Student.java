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
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Öğrencinin benzersiz tanımlayıcısı")
    private Long id;
    @Schema(description = "Öğrencinin adı")
    private String firstName;
    @Schema(description = "Öğrencinin soyadı")
    private String lastName;
    @Schema(description = "Öğrencinin doğum tarihi")
    private String birthDate;
    @Schema(description = "Öğrencinin kayıt tarihi")
    private String registrationDate;
    @Schema(description = "Öğrencinin e-posta adresi")
    private String email;
    @Schema(description = "Öğrencinin telefon numarası")
    private String phoneNumber;
    @Schema(description = "Öğrencinin aktiflik durumu")
    private int isActive;
}
