package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.model.ClassroomRel;

import java.util.List;
import java.util.Optional;

public interface ClassroomRelService {
    List<ClassroomRel> getAllRelations();
    Optional<ClassroomRel> getRelationById(Long id);
    ClassroomRel createRelation(ClassroomRel classroomRel);
    ClassroomRel updateRelation(Long id, ClassroomRel updatedRelation);
    void deleteRelation(Long id);
}
