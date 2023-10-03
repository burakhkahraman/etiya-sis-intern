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
@Table(name = "classrooms")
@Schema(description = "Sınıfların detay bilgileri için model")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Sınıfın benzersiz tanımlayıcısı")
    private Long id;
    @Schema(description = "Sınıfın ismi")
    private String name;
    @Schema(description = "Sınıfın açıklaması")
    private String description;
    @Schema(description = "Sınıfın konumu")
    private String location;
    @Schema(description = "Sınıfın alabileceği maksimum öğrenci sayısı")
    private Integer maxStudentCount;
    @Schema(description = "Sınıfın aktiflik durumu")
    private int isActive;
    @Schema(description = "Sınıfın kısa kodu")
    @Column(unique = true)  // Eğer shortCode'un benzersiz olmasını istiyorsanız bu anotasyonu ekleyin.
    private String shortCode;
}
