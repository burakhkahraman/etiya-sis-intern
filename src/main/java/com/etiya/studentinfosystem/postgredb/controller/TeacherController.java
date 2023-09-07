package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.dto.TeacherDTO;
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
import java.util.stream.Collectors;

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
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable long id) {
        TeacherDTO teacherDTO = teacherService.getTeacherById(id);
        if (teacherDTO != null) {
            return ResponseEntity.ok(teacherDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Tüm öğretmenleri listeler")
    @GetMapping("/")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @Operation(summary = "Yeni bir öğretmen ekler")
    @PostMapping("/")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        TeacherDTO newTeacher = teacherService.createTeacher(teacherDTO);
        return ResponseEntity.ok(newTeacher);
    }

    @Operation(summary = "Bir öğretmenin bilgilerini günceller")
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable long id, @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO updatedTeacher = teacherService.updateTeacher(id, teacherDTO);
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
