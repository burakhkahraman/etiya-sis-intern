package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.model.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    List<Grade> findAllGrades();
    Optional<Grade> findGradeById(Long id);
    Grade saveGrade(Grade grade);
    void deleteGradeById(Long id);

}
