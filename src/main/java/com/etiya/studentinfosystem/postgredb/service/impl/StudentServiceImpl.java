package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.model.Student;
import com.etiya.studentinfosystem.postgredb.repository.StudentRepository;
import com.etiya.studentinfosystem.postgredb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        if(studentRepository.existsById(id)) {
            student.setId(id);
            return studentRepository.save(student);
        }
        return null;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }


}
