package com.etiya.studentinfosystem.postgredb.repository;

import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface TakenCourseRepository extends JpaRepository<TakenCourse, Long> {
    List<TakenCourse> findByStudentId(Long studentId);
    Optional<TakenCourse> findByStudentIdAndCourseId(Long studentId, Long courseId);

    List<TakenCourse> findAllByGradeIsNull();

}
