package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.dto.TakenCourseDTO;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;

import java.util.List;
import java.util.Optional;

public interface TakenCourseService {
    List<TakenCourseDTO> getAllTakenCourses();

    Optional<TakenCourseDTO> getTakenCourseById(Long id);

    TakenCourseDTO createTakenCourse(TakenCourseDTO takenCourseDTO);

    TakenCourseDTO updateTakenCourse(Long id, TakenCourseDTO takenCourseDTO);

    void deleteTakenCourse(Long id);
}
