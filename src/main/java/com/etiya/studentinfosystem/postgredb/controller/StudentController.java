package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.dto.TakenCourseDTO;
import com.etiya.studentinfosystem.postgredb.model.Student;
import com.etiya.studentinfosystem.postgredb.service.StudentService;
import com.etiya.studentinfosystem.postgredb.service.TakenCourseService;
import com.etiya.studentinfosystem.postgredb.service.impl.StudentInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student", description = "Öğrenci yönetimi")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TakenCourseService takenCourseService;

    // CREATE
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(newStudent);
    }

    // READ
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{studentId}/coursesWithGrades")
    public ResponseEntity<List<TakenCourseDTO>> getStudentCoursesWithGrades(@PathVariable Long studentId) {
        List<TakenCourseDTO> dtos = takenCourseService.getStudentCoursesWithGrades(studentId);
        return ResponseEntity.ok(dtos);
    }
}
