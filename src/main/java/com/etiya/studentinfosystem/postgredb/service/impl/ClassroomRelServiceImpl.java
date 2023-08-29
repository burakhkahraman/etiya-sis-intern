package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.model.ClassroomRel;
import com.etiya.studentinfosystem.postgredb.repository.ClassroomRelRepository;
import com.etiya.studentinfosystem.postgredb.service.ClassroomRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomRelServiceImpl implements ClassroomRelService {
    @Autowired
    private ClassroomRelRepository classroomRelRepository;

    @Override
    public List<ClassroomRel> getAllRelations() {
        return classroomRelRepository.findAll();
    }

    @Override
    public Optional<ClassroomRel> getRelationById(Long id) {
        return classroomRelRepository.findById(id);
    }

    @Override
    public ClassroomRel createRelation(ClassroomRel classroomRel) {
        return classroomRelRepository.save(classroomRel);
    }

    @Override
    public ClassroomRel updateRelation(Long id, ClassroomRel updatedRelation) {
        if (classroomRelRepository.existsById(id)) {
            updatedRelation.setId(id);
            return classroomRelRepository.save(updatedRelation);
        }
        return null;
    }

    @Override
    public void deleteRelation(Long id) {
        classroomRelRepository.deleteById(id);
    }
}
