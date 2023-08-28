package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.model.Classroom;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {
    List<Classroom> getAllClassrooms();
    Optional<Classroom> getClassroomById(Long id);
    Classroom createClassroom(Classroom classroom);
    Classroom updateClassroom(Long id, Classroom classroom);
    void deleteClassroom(Long id);
}
