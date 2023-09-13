package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.dto.TakenCourseDTO;
import com.etiya.studentinfosystem.postgredb.model.Course;
import com.etiya.studentinfosystem.postgredb.model.Grade;
import com.etiya.studentinfosystem.postgredb.model.Student;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import com.etiya.studentinfosystem.postgredb.repository.CourseRepository;
import com.etiya.studentinfosystem.postgredb.repository.StudentRepository;
import com.etiya.studentinfosystem.postgredb.repository.TakenCourseRepository;
import com.etiya.studentinfosystem.postgredb.request.TakenCourseRequest;
import com.etiya.studentinfosystem.postgredb.service.TakenCourseService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private final Mapper dozerMapper = DozerBeanMapperBuilder.create()
            .withMappingFiles("TakenCourseDozer.xml")
            .build();

    @Override
    public List<TakenCourseDTO> getAllTakenCourses() {
        return takenCourseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TakenCourseDTO> getTakenCourseById(Long id) {
        return takenCourseRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public TakenCourse createTakenCourse(TakenCourseRequest request) {
        TakenCourse takenCourse = dozerMapper.map(request, TakenCourse.class);
        resolveDependenciesForTakenCourse(takenCourse, request);
        return takenCourseRepository.save(takenCourse);
    }

    @Override
    @Transactional
    public TakenCourse updateTakenCourse(TakenCourseRequest request) {
        TakenCourse existingTakenCourse = takenCourseRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Taken course not found for ID: " + request.getId()));

        dozerMapper.map(request, existingTakenCourse);
        resolveDependenciesForTakenCourse(existingTakenCourse, request);

        return takenCourseRepository.save(existingTakenCourse);
    }

    private void resolveDependenciesForTakenCourse(TakenCourse takenCourse, TakenCourseRequest request) {
        Course course = courseRepository.findByShortCode(request.getCourseShrtCode())
                .orElseThrow(() -> new IllegalArgumentException("Course not found for shortCode: " + request.getCourseShrtCode()));
        takenCourse.setCourse(course);

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found for ID: " + request.getStudentId()));
        takenCourse.setStudent(student);
    }

    @Override
    public void deleteTakenCourse(Long id) {
        takenCourseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Taken course not found for ID: " + id));
        takenCourseRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<String> canStudentTakeCourse(Long studentId, Long courseId) {
        // Öğrenci kontrolü
        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Öğrenci ID'si " + studentId + " olan öğrenci bulunamadı.");
        }

        // Ders kontrolü
        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException("Course ID'si " + courseId + " olan ders bulunamadı.");
        }

        Optional<TakenCourse> takenCourseOpt = takenCourseRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (takenCourseOpt.isPresent()) {
            Grade grade = takenCourseOpt.get().getGrade();
            if (grade == null) {
                return  ResponseEntity.ok("Öğrenci dersi zaten aktif olarak alıyor tekrardan alamaz.");  // Notu olmayan bir dersi tekrar alamaz
            }

            if (grade.getMinScore()>=60){
                return  ResponseEntity.ok("geçtiniginiz dersi alamazsınız ");  // Notu olmayan bir dersi tekrar alamaz

            }
        }
        return  ResponseEntity.ok("Öğrenci bu dersi alabilir.");  // Diğer tüm durumlarda dersi alabilir
    }

    private TakenCourseDTO convertToDTO(TakenCourse takenCourse) {
        return dozerMapper.map(takenCourse, TakenCourseDTO.class);
    }
}