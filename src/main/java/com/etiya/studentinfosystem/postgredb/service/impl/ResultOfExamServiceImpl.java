package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.dto.ResultOfExamDTO;
import com.etiya.studentinfosystem.postgredb.model.ExamType;
import com.etiya.studentinfosystem.postgredb.model.ResultOfExam;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import com.etiya.studentinfosystem.postgredb.repository.ExamTypeRepository;
import com.etiya.studentinfosystem.postgredb.repository.ResultOfExamRepository;
import com.etiya.studentinfosystem.postgredb.repository.TakenCourseRepository;
import com.etiya.studentinfosystem.postgredb.service.ResultOfExamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultOfExamServiceImpl implements ResultOfExamService {
    @Autowired
    private ResultOfExamRepository resultOfExamRepository;

    @Autowired
    private ExamTypeRepository examTypeRepository;

    @Autowired
    private TakenCourseRepository takenCourseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ResultOfExamDTO> getAllResults() {
        return resultOfExamRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ResultOfExamDTO> getResultById(Long id) {
        return resultOfExamRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public ResultOfExamDTO createResult(ResultOfExamDTO resultDTO) {
        ResultOfExam result = convertToEntity(resultDTO);
        result = resultOfExamRepository.save(result);
        return convertToDTO(result);
    }

    @Override
    public Optional<ResultOfExamDTO> updateResult(Long id, ResultOfExamDTO resultDTO) {
        if (resultOfExamRepository.existsById(id)) {
            ResultOfExam resultToUpdate = resultOfExamRepository.findById(id).orElse(null);
            if (resultToUpdate != null) {
                modelMapper.map(resultDTO, resultToUpdate);
                resultOfExamRepository.save(resultToUpdate);
                return Optional.of(convertToDTO(resultToUpdate));
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteResult(Long id) {
        if (resultOfExamRepository.existsById(id)) {
            resultOfExamRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ResultOfExam> getResultsForStudentInCourse(Long studentId, Long courseId) {
        TakenCourse takenCourse = takenCourseRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new RuntimeException("The student has not taken this course."));
        return resultOfExamRepository.findByTakenCourse(takenCourse);
    }

    private ResultOfExamDTO convertToDTO(ResultOfExam result) {
        ResultOfExamDTO dto = modelMapper.map(result, ResultOfExamDTO.class);


        // Exam Type Name
        if (result.getExamType() != null) {
            dto.setExamTypeName(result.getExamType().getExamName());
        }

        dto.setScore(Double.valueOf(result.getScore()));

        return dto;
    }

    private ResultOfExam convertToEntity(ResultOfExamDTO resultDTO) {
        ResultOfExam result = modelMapper.map(resultDTO, ResultOfExam.class);
        return result;
    }
}
