package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Teacher getTeacherById(Long id);
    List<Teacher> getAllTeachers();
    Teacher createTeacher(Teacher teacher);
    Teacher updateTeacher(Long id, Teacher teacherDetails);
    boolean deleteTeacher(Long id);
}
