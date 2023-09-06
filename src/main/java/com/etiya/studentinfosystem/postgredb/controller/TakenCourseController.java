package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.dto.TakenCourseDTO;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import com.etiya.studentinfosystem.postgredb.request.TakenCourseRequest;
import com.etiya.studentinfosystem.postgredb.service.TakenCourseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taken-courses")
public class TakenCourseController {
    @Autowired
    private TakenCourseService takenCourseService;

    @GetMapping
    @Operation(summary = "Tüm alınan dersleri listeler")
    public List<TakenCourseDTO> getAllTakenCourses() {
        return takenCourseService.getAllTakenCourses();
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID ile alınan ders detayını getirir")
    public TakenCourseDTO getTakenCourseById(@PathVariable Long id) {
        return takenCourseService.getTakenCourseById(id).orElse(null);
    }

    @PostMapping
    @Operation(summary = "Yeni bir alınan ders kaydı oluşturur")
    public TakenCourse createTakenCourse(@RequestBody TakenCourseRequest takenCourseRequest) {
        return takenCourseService.createTakenCourse(takenCourseRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "ID ile alınan ders kaydını günceller")
    public TakenCourse updateTakenCourse(@RequestBody TakenCourseRequest takenCourseRequest) {
        return takenCourseService.updateTakenCourse(takenCourseRequest);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "ID ile alınan ders kaydını siler")
    public void deleteTakenCourse(@PathVariable Long id) {
        takenCourseService.deleteTakenCourse(id);
    }
}
