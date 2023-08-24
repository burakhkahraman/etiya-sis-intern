package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> findAllTeachers();
    Optional<Teacher> findTeacherById(Long id);
    Teacher saveTeacher(Teacher teacher);
    void deleteTeacherById(Long id);
}
