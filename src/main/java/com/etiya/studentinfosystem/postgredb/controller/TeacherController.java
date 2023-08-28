package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.model.Teacher;
import com.etiya.studentinfosystem.postgredb.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@Tag(name = "Teacher", description = "Teacher Controller açıklaması")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Operation(summary = "Öğretmen detaylarını getirir")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Öğretmen bulundu"),
            @ApiResponse(responseCode = "404", description = "Öğretmen bulunamadı")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(
            @Parameter(description = "ID ile öğretmen bilgilerini alır") @PathVariable long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        if (teacher != null) {
            return ResponseEntity.ok(teacher);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Tüm öğretmenleri listeler")
    @GetMapping("/")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @Operation(summary = "Yeni bir öğretmen ekler")
    @PostMapping("/")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher newTeacher = teacherService.createTeacher(teacher);
        return ResponseEntity.ok(newTeacher);
    }

    @Operation(summary = "Bir öğretmenin bilgilerini günceller")
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable long id, @RequestBody Teacher teacherDetails) {
        Teacher updatedTeacher = teacherService.updateTeacher(id, teacherDetails);
        if (updatedTeacher != null) {
            return ResponseEntity.ok(updatedTeacher);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Bir öğretmeni siler")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable long id) {
        boolean isDeleted = teacherService.deleteTeacher(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
