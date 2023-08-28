package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.model.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);

    List<Student> findAllStudents();

    Student findStudentById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
