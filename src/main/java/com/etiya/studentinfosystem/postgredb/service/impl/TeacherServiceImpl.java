package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.model.Teacher;
import com.etiya.studentinfosystem.postgredb.repository.TeacherRepository;
import com.etiya.studentinfosystem.postgredb.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher.orElse(null);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        Optional<Teacher> existingTeacherOpt = teacherRepository.findById(id);
        if (existingTeacherOpt.isPresent()) {
            Teacher existingTeacher = existingTeacherOpt.get();
            existingTeacher.setFirstName(teacherDetails.getFirstName());
            existingTeacher.setLastName(teacherDetails.getLastName());
            existingTeacher.setEmail(teacherDetails.getEmail());
            // Diğer alanları da burada güncelleyebilirsiniz.
            return teacherRepository.save(existingTeacher);
        }
        return null;
    }

    @Override
    public boolean deleteTeacher(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
