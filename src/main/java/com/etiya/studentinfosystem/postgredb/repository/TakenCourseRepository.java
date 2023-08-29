package com.etiya.studentinfosystem.postgredb.repository;

import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakenCourseRepository extends JpaRepository<TakenCourse, Long> {
}
