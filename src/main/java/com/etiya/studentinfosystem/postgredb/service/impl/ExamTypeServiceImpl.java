package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.model.ExamType;
import com.etiya.studentinfosystem.postgredb.repository.ExamTypeRepository;
import com.etiya.studentinfosystem.postgredb.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamTypeServiceImpl implements ExamTypeService {
    @Autowired
    private ExamTypeRepository examTypeRepository;

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
}
