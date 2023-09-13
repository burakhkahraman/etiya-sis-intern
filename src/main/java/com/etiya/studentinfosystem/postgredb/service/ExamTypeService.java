package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.model.ExamType;

import java.util.List;

public interface ExamTypeService {
    List<ExamType> getAllExamTypes();
    ExamType getExamTypeById(Long id);
    ExamType createExamType(ExamType examType);
    ExamType updateExamType(Long id, ExamType examType);
    void deleteExamType(Long id);

    List<ExamType> findByExamGroup(Long examGroupId);

    Double calculateAverageGradeForStudentInCourse(Long studentId, Long courseId);


}
