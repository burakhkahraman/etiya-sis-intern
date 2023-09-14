package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.dto.ResultOfExamDTO;
import com.etiya.studentinfosystem.postgredb.dto.StudentTakenCoursesDTO;
import com.etiya.studentinfosystem.postgredb.model.ResultOfExam;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import com.etiya.studentinfosystem.postgredb.repository.ResultOfExamRepository;
import com.etiya.studentinfosystem.postgredb.repository.TakenCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentInfoServiceImpl {
    @Autowired
    private TakenCourseRepository takenCourseRepository;

    @Autowired
    private ResultOfExamRepository resultOfExamRepository;

    public List<StudentTakenCoursesDTO> getStudentCoursesWithGrades(Long studentId) {
        return takenCourseRepository.findByStudentId(studentId).stream()
                .map(this::mapToStudentTakenCoursesDTO)
                .collect(Collectors.toList());
    }

    private StudentTakenCoursesDTO mapToStudentTakenCoursesDTO(TakenCourse takenCourse) {
        StudentTakenCoursesDTO dto = new StudentTakenCoursesDTO();
        dto.setStudentId(takenCourse.getStudent().getId());
        dto.setCourseName(takenCourse.getCourse().getCourseName());
        dto.setTerm(takenCourse.getTerm());

        List<ResultOfExam> examResults = resultOfExamRepository.findByTakenCourse(takenCourse);
        dto.setExamResults(examResults.stream()
                .map(this::mapToResultOfExamDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    private ResultOfExamDTO mapToResultOfExamDTO(ResultOfExam result) {
        ResultOfExamDTO dto = new ResultOfExamDTO();
        dto.setCourseName(result.getTakenCourse().getCourse().getCourseName());
        dto.setExamTypeName(result.getExamType().getExamName());
        dto.setScore(Double.valueOf(result.getScore()));
        return dto;
    }
}
