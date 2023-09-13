package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.dto.ResultOfExamDTO;
import com.etiya.studentinfosystem.postgredb.model.ResultOfExam;

import java.util.List;
import java.util.Optional;

public interface ResultOfExamService {
    List<ResultOfExamDTO> getAllResults();
    Optional<ResultOfExamDTO> getResultById(Long id);
    ResultOfExamDTO createResult(ResultOfExamDTO resultDTO);
    Optional<ResultOfExamDTO> updateResult(Long id, ResultOfExamDTO resultDTO);
    boolean deleteResult(Long id);
    List<ResultOfExam> getResultsForStudentInCourse(Long studentId, Long courseId);

}
