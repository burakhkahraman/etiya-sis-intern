package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.model.Grade;
import com.etiya.studentinfosystem.postgredb.service.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grades")
@Tag(name = "Grade", description = "Grade Controller Açıklaması")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @Operation(summary = "Tüm notları getir")
    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades() {
        List<Grade> grades = gradeService.findAllGrades();
        return ResponseEntity.ok(grades);
    }

    @Operation(summary = "ID'ye göre notu getir")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Başarılı"),
            @ApiResponse(responseCode = "404", description = "Not bulunamadı")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
        Optional<Grade> grade = gradeService.findGradeById(id);
        if (grade.isPresent()) {
            return ResponseEntity.ok(grade.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Yeni bir not oluştur")
    @PostMapping
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
        Grade savedGrade = gradeService.saveGrade(grade);
        return ResponseEntity.ok(savedGrade);
    }

    @Operation(summary = "Notu güncelle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Başarılı"),
            @ApiResponse(responseCode = "404", description = "Not bulunamadı")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        if (!gradeService.findGradeById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        grade.setId(id);
        Grade updatedGrade = gradeService.saveGrade(grade);
        return ResponseEntity.ok(updatedGrade);
    }

    @Operation(summary = "Notu sil")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGradeById(id);
        return ResponseEntity.noContent().build();
    }
}
