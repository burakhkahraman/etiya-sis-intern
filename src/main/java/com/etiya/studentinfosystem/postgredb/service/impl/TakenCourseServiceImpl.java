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

    // DTO'dan entity'ye dönüşüm
    private TakenCourse convertToEntity(TakenCourseDTO dto) {
        TakenCourse takenCourse = new TakenCourse();

        // Course, Student ve Grade nesnelerini ID'lere göre çekiyoruz.
        Course course = courseRepository.findById(dto.getCourseId()).orElse(null);
        Student student = studentRepository.findById(dto.getStudentId()).orElse(null);
        Grade grade = gradeRepository.findById(dto.getGradeId()).orElse(null);

        takenCourse.setCourse(course);
        takenCourse.setStudent(student);
        takenCourse.setGrade(grade);
        takenCourse.setTerm(dto.getTerm());
        takenCourse.setIsActive(dto.getIsActive());
        takenCourse.setShortCode(dto.getShortCode());

        return takenCourse;
    }

    // Entity'den DTO'ya dönüşüm
    private TakenCourseDTO convertToDTO(TakenCourse takenCourse) {
        TakenCourseDTO dto = new TakenCourseDTO();
        dto.setId(takenCourse.getId());

        dto.setCourseId(takenCourse.getCourse().getId());
        dto.setStudentId(takenCourse.getStudent().getId());
        dto.setGradeId(takenCourse.getGrade().getId());
        dto.setTerm(takenCourse.getTerm());
        dto.setIsActive(takenCourse.getIsActive());
        dto.setShortCode(takenCourse.getShortCode());

        return dto;
    }

    @Override
    public List<TakenCourseDTO> getAllTakenCourses() {
        List<TakenCourse> takenCourses = takenCourseRepository.findAll();
        return takenCourses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<TakenCourseDTO> getTakenCourseById(Long id) {
        return takenCourseRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public TakenCourseDTO createTakenCourse(TakenCourseDTO dto) {
        TakenCourse takenCourse = convertToEntity(dto);

        // Bu noktada gelen verilerde eksiklik veya null kontrolü yapmanızı tavsiye ederim.
        if(takenCourse.getCourse() == null || takenCourse.getStudent() == null || takenCourse.getGrade() == null) {
            throw new RuntimeException("Course, Student, or Grade information is missing.");
        }

        TakenCourse saved = takenCourseRepository.save(takenCourse);
        return convertToDTO(saved);
    }

    @Override
    public TakenCourseDTO updateTakenCourse(Long id, TakenCourseDTO dto) {
        TakenCourse existingTakenCourse = takenCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taken course not found"));

        TakenCourse updatedTakenCourse = convertToEntity(dto);
        updatedTakenCourse.setId(existingTakenCourse.getId());

        TakenCourse saved = takenCourseRepository.save(updatedTakenCourse);
        return convertToDTO(saved);
    }

    @Override
    public void deleteTakenCourse(Long id) {
        takenCourseRepository.deleteById(id);
    }
}
