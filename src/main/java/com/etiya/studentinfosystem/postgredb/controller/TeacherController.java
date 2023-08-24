package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.model.Teacher;
import com.etiya.studentinfosystem.postgredb.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "Öğretmen detaylarını getirir", response = Teacher.class)
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@ApiParam(value = "ID ile öğretmen bilgilerini alır") @PathVariable long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        if (teacher != null) {
            return ResponseEntity.ok(teacher);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Tüm öğretmenleri listeler", response = List.class)
    @GetMapping("/")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @ApiOperation(value = "Yeni bir öğretmen ekler")
    @PostMapping("/")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher newTeacher = teacherService.createTeacher(teacher);
        return ResponseEntity.ok(newTeacher);
    }

    @ApiOperation(value = "Bir öğretmenin bilgilerini günceller")
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable long id, @RequestBody Teacher teacherDetails) {
        Teacher updatedTeacher = teacherService.updateTeacher(id, teacherDetails);
        if (updatedTeacher != null) {
            return ResponseEntity.ok(updatedTeacher);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Bir öğretmeni siler")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable long id) {
        boolean isDeleted = teacherService.deleteTeacher(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
