package com.etiya.studentinfosystem.postgredb.repository;

import com.etiya.studentinfosystem.postgredb.model.ExamType;
import com.etiya.studentinfosystem.postgredb.model.ResultOfExam;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ResultOfExamRepository extends JpaRepository<ResultOfExam, Long> {
    List<ResultOfExam> findByTakenCourse(TakenCourse takenCourse);

    ResultOfExam findByTakenCourseAndExamType(TakenCourse takenCourse, ExamType examType);


}
