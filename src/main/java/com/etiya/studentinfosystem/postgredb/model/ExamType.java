package com.etiya.studentinfosystem.postgredb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exam_type")
@Data
@Schema(description = "Sınav türlerinin detayları için model")
public class ExamType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_type_id")
    @Schema(description = "Sınav türüne dair benzersiz tanımlayıcı")
    private Long id;

    @Column(name = "exam_name")
    @Schema(description = "Sınavın adı")
    private String examName;

    @Column(name = "weight")
    @Schema(description = "Sınavın ortalamaya olan etkisi")
    private Double weight;

    @Column(name = "parent_exam_type_id")
    @Schema(description = "Eğer bütünleme sınavına kalındıysa, diğer koşulları da göz önüne alarak final notunun ortalamaya dahil edilmemesini sağlayacak olan parentExamTypeID")
    private Long parentExamTypeId;

    @Column(name = "short_code")
    @Schema(description = "Sınavın kısa kodu")
    private String shortCode;

    @Column(name = "is_active")
    @Schema(description = "Sınav türünün aktif olup olmadığına dair durum")
    private int isActive;

    @Column(name = "exam_group")
    @Schema(description = "Sınav türlerini gruplayan alan")
    private Long examGroup;


/*    @OneToMany(mappedBy = "examType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<ResultOfExam> results = new ArrayList<>();*/
}
