package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.dto.TakenCourseDTO;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import com.etiya.studentinfosystem.postgredb.request.TakenCourseRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TakenCourseService {
    List<TakenCourseDTO> getAllTakenCourses();

    Optional<TakenCourseDTO> getTakenCourseById(Long id);

    TakenCourse createTakenCourse(TakenCourseRequest takenCourseRequest);

    TakenCourse updateTakenCourse(TakenCourseRequest takenCourseRequest);

    void deleteTakenCourse(Long id);

    ResponseEntity<String> canStudentTakeCourse(Long studentId, Long courseId);

    List<TakenCourseDTO> getStudentCoursesWithGrades(Long studentId);
}

