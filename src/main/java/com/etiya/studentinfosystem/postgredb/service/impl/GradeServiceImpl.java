package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.model.Grade;
import com.etiya.studentinfosystem.postgredb.repository.GradeRepository;
import com.etiya.studentinfosystem.postgredb.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public List<Grade> findAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Optional<Grade> findGradeById(Long id) {
        return gradeRepository.findById(id);
    }

    @Override
    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public void deleteGradeById(Long id) {
        gradeRepository.deleteById(id);
    }
}
