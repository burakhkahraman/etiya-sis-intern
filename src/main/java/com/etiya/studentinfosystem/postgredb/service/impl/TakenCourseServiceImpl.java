package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.dto.TakenCourseDTO;
import com.etiya.studentinfosystem.postgredb.model.Course;
import com.etiya.studentinfosystem.postgredb.model.Grade;
import com.etiya.studentinfosystem.postgredb.model.Student;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import com.etiya.studentinfosystem.postgredb.repository.CourseRepository;
import com.etiya.studentinfosystem.postgredb.repository.GradeRepository;
import com.etiya.studentinfosystem.postgredb.repository.StudentRepository;
import com.etiya.studentinfosystem.postgredb.repository.TakenCourseRepository;
import com.etiya.studentinfosystem.postgredb.service.TakenCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TakenCourseServiceImpl implements TakenCourseService {
    @Autowired
    private TakenCourseRepository takenCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;

    private TakenCourse convertToEntity(TakenCourseDTO dto) {
        TakenCourse takenCourse = new TakenCourse();

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found for ID: " + dto.getCourseId()));
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found for ID: " + dto.getStudentId()));
        Grade grade = gradeRepository.findById(dto.getGradeId())
                .orElseThrow(() -> new RuntimeException("Grade not found for ID: " + dto.getGradeId()));

        takenCourse.setCourse(course);
        takenCourse.setStudent(student);
        takenCourse.setGrade(grade);
        takenCourse.setTerm(dto.getTerm());
        takenCourse.setIsActive(dto.getIsActive());

        return takenCourse;
    }

    private TakenCourseDTO convertToDTO(TakenCourse takenCourse) {
        TakenCourseDTO dto = new TakenCourseDTO();
        dto.setId(takenCourse.getId());
        dto.setCourseId(takenCourse.getCourse().getId());
        dto.setStudentId(takenCourse.getStudent().getId());
        dto.setGradeId(takenCourse.getGrade().getId());
        dto.setTerm(takenCourse.getTerm());
        dto.setIsActive(takenCourse.getIsActive());

        return dto;
    }

    @Override
    public List<TakenCourseDTO> getAllTakenCourses() {
        return takenCourseRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<TakenCourseDTO> getTakenCourseById(Long id) {
        return takenCourseRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public TakenCourseDTO createTakenCourse(TakenCourseDTO dto) {
        TakenCourse takenCourse = convertToEntity(dto);
        TakenCourse saved = takenCourseRepository.save(takenCourse);
        return convertToDTO(saved);
    }

    @Override
    public TakenCourseDTO updateTakenCourse(Long id, TakenCourseDTO dto) {
        if (!takenCourseRepository.existsById(id)) {
            throw new RuntimeException("Taken course not found for ID: " + id);
        }
        TakenCourse updatedTakenCourse = convertToEntity(dto);
        updatedTakenCourse.setId(id);
        TakenCourse saved = takenCourseRepository.save(updatedTakenCourse);
        return convertToDTO(saved);
    }

    @Override
    public void deleteTakenCourse(Long id) {
        if (!takenCourseRepository.existsById(id)) {
            throw new RuntimeException("Taken course not found for ID: " + id);
        }
        takenCourseRepository.deleteById(id);
    }
}
