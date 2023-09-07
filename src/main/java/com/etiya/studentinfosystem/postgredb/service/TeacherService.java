package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.dto.TeacherDTO;
import com.etiya.studentinfosystem.postgredb.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    TeacherDTO getTeacherById(Long id);

    List<TeacherDTO> getAllTeachers();

    TeacherDTO createTeacher(TeacherDTO teacherDTO);

    TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO);

    boolean deleteTeacher(Long id);


}
