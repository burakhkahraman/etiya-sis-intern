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
        ResultOfExamDTO savedDTO = convertToDTO(result);

        savedDTO.setExamTypeId(result.getExamType().getId());
        savedDTO.setTakenCourseId(result.getTakenCourse().getId());

        return savedDTO;
    }

    @Override
    public Optional<ResultOfExamDTO> updateResult(Long id, ResultOfExamDTO resultDTO) {
        if (examTypeRepository.existsById(id)) {
            ResultOfExam resultToUpdate = resultOfExamRepository.findById(id).orElse(null);

            if (resultToUpdate != null) {
                // DTO'dan gelen bilgileri varlık nesnesine aktar
                ResultOfExam resultFromDto = convertToEntity(resultDTO);
                resultToUpdate.setScore(resultFromDto.getScore());
                /*resultToUpdate.setShortCode(resultFromDto.getShortCode());*/
                resultToUpdate.setIsActive(resultFromDto.getIsActive());

                // Eğer DTO'da ilişkilendirilmiş varlıkların ID'leri varsa güncelle
                if (resultDTO.getExamTypeId() != null) {
                    ExamType examType = examTypeRepository.findById(resultDTO.getExamTypeId()).orElse(null);
                    resultToUpdate.setExamType(examType);
                }
                if (resultDTO.getTakenCourseId() != null) {
                    TakenCourse takenCourse = takenCourseRepository.findById(resultDTO.getTakenCourseId()).orElse(null);
                    resultToUpdate.setTakenCourse(takenCourse);
                }

                // Değişiklikleri kaydet
                resultOfExamRepository.save(resultToUpdate);

                // Güncellenen varlığı DTO'ya dönüştür ve geri döndür
                ResultOfExamDTO updatedDto = convertToDTO(resultToUpdate);
                updatedDto.setExamTypeId(resultToUpdate.getExamType().getId());
                updatedDto.setTakenCourseId(resultToUpdate.getTakenCourse().getId());

                return Optional.of(updatedDto);
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
        return modelMapper.map(result, ResultOfExamDTO.class);
    }

    private ResultOfExam convertToEntity(ResultOfExamDTO resultDTO) {
        ResultOfExam result = modelMapper.map(resultDTO, ResultOfExam.class);

        if (resultDTO.getExamTypeId() != null) {
            ExamType examType = examTypeRepository.findById(resultDTO.getExamTypeId()).orElse(null);
            result.setExamType(examType);
        }

        if (resultDTO.getTakenCourseId() != null) {
            TakenCourse takenCourse = takenCourseRepository.findById(resultDTO.getTakenCourseId()).orElse(null);
            result.setTakenCourse(takenCourse);
        }

        return result;
    }
}
