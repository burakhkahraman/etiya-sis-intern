package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.model.ExamType;
import com.etiya.studentinfosystem.postgredb.repository.ExamTypeRepository;
import com.etiya.studentinfosystem.postgredb.service.ExamTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-types")
public class ExamTypeController {
    @Autowired
    private ExamTypeService examTypeService;



    @GetMapping
    @Operation(summary = "Tüm sınav türlerini listeler")
    public ResponseEntity<List<ExamType>> getAllExamTypes() {
        return new ResponseEntity<>(examTypeService.getAllExamTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Belirtilen ID'ye sahip sınav türünü getirir")
    public ResponseEntity<ExamType> getExamTypeById(@PathVariable Long id) {
        ExamType examType = examTypeService.getExamTypeById(id);
        if (examType != null) {
            return new ResponseEntity<>(examType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Yeni bir sınav türü oluşturur")
    public ResponseEntity<ExamType> createExamType(@RequestBody ExamType examType) {
        ExamType created = examTypeService.createExamType(examType);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Belirtilen ID'ye sahip sınav türünü günceller")
    public ResponseEntity<ExamType> updateExamType(@PathVariable Long id, @RequestBody ExamType examType) {
        ExamType updated = examTypeService.updateExamType(id, examType);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Belirtilen ID'ye sahip sınav türünü siler")
    public ResponseEntity<Void> deleteExamType(@PathVariable Long id) {
        try {
            examTypeService.deleteExamType(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/group/{examGroup}")
    public ResponseEntity<List<ExamType>> getExamTypesByGroup(@PathVariable Long examGroup) {
        List<ExamType> examTypes = examTypeService.findByExamGroup(examGroup);
        if (examTypes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(examTypes);
    }

    @GetMapping("/average-grade/{studentId}/{courseId}")
    public ResponseEntity<Double> getAverageGradeForStudentInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        Double averageGrade = examTypeService.calculateAverageGradeForStudentInCourse(studentId, courseId);
        return ResponseEntity.ok(averageGrade);
    }
}
