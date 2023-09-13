package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.model.ExamType;
import com.etiya.studentinfosystem.postgredb.model.ResultOfExam;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import com.etiya.studentinfosystem.postgredb.repository.ExamTypeRepository;
import com.etiya.studentinfosystem.postgredb.repository.ResultOfExamRepository;
import com.etiya.studentinfosystem.postgredb.repository.TakenCourseRepository;
import com.etiya.studentinfosystem.postgredb.service.ExamTypeService;
import com.etiya.studentinfosystem.postgredb.service.ResultOfExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ExamTypeServiceImpl implements ExamTypeService {

    @Autowired
    private ExamTypeRepository examTypeRepository;

    @Autowired
    private TakenCourseRepository takenCourseRepository;

    @Autowired
    private ResultOfExamRepository resultOfExamRepository;

    @Override
    public List<ExamType> getAllExamTypes() {
        return examTypeRepository.findAll();
    }

    @Override
    public ExamType getExamTypeById(Long id) {
        return examTypeRepository.findById(id).orElse(null);
    }

    @Override
    public ExamType createExamType(ExamType examType) {
        return examTypeRepository.save(examType);
    }

    @Override
    public ExamType updateExamType(Long id, ExamType examType) {
        if (examTypeRepository.existsById(id)) {
            examType.setId(id);
            return examTypeRepository.save(examType);
        }
        return null;
    }

    @Override
    public void deleteExamType(Long id) {
        examTypeRepository.deleteById(id);
    }

    @Override
    public List<ExamType> findByExamGroup(Long examGroup) {
        return examTypeRepository.findByExamGroup(examGroup);
    }

    @Override
    public Double calculateAverageGradeForStudentInCourse(Long studentId, Long courseId) {
        TakenCourse takenCourse = takenCourseRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new RuntimeException("The student has not taken this course."));

        List<ResultOfExam> results = resultOfExamRepository.findByTakenCourse(takenCourse);

        if (results.isEmpty()) {
            throw new RuntimeException("No exam results found for the student in this course.");
        }

        adjustResultsForButunleme(results);

        double weightedSum = 0.0;
        for (ResultOfExam result : results) {
            ExamType examType = result.getExamType();
            double weight = getWeightForExamType(examType.getExamGroup(), examType);
            weightedSum += result.getScore() * weight;
        }

        return weightedSum;
    }

    private void adjustResultsForButunleme(List<ResultOfExam> results) {
        ResultOfExam butunleme = results.stream().filter(result -> result.getExamType().getParentExamTypeId() != null).findFirst().orElse(null);

        if (butunleme != null) {
            results.removeIf(r -> r.getExamType().getId().equals(butunleme.getExamType().getParentExamTypeId()));
        }
    }

    private double getWeightForExamType(Long examGroupId, ExamType examType) {
        if (examGroupId == 1) {
            if (examType.getExamName().equals("VIZE")) {
                return 0.4;
            } else {
                return 0.6;
            }
        } else if (examGroupId == 2) {
            if (examType.getExamName().equals("VIZE")) {
                return 0.3;
            } else if (examType.getExamName().equals("FINAL") || examType.getExamName().equals("BUTUNLEME")) {
                return 0.5;
            } else {
                return 0.2;  // LAB
            }
        }
        throw new RuntimeException("Invalid exam group or exam type.");
    }
}
