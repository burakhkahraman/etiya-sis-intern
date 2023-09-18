package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.model.StudentPerformance;
import com.etiya.studentinfosystem.postgredb.service.StudentPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentPerformanceController {

    @Autowired
    private StudentPerformanceService studentPerformanceService;

    @GetMapping("/{studentId}/performance-analysis")
    public ResponseEntity<StudentPerformance> getStudentPerformanceAnalysis(@PathVariable Long studentId) {
        StudentPerformance performance = studentPerformanceService.analyzeStudentPerformance(studentId);
        return ResponseEntity.ok(performance);
    }
}
