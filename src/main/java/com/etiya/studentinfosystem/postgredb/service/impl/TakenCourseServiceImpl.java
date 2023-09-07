/*
package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.dto.TakenCourseDTO;
import com.etiya.studentinfosystem.postgredb.model.Course;
import com.etiya.studentinfosystem.postgredb.model.Student;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import com.etiya.studentinfosystem.postgredb.repository.CourseRepository;

import com.etiya.studentinfosystem.postgredb.repository.StudentRepository;
import com.etiya.studentinfosystem.postgredb.repository.TakenCourseRepository;
import com.etiya.studentinfosystem.postgredb.request.TakenCourseRequest;
import com.etiya.studentinfosystem.postgredb.service.TakenCourseService;

import com.github.dozermapper.core.Mapper;
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

    private Mapper dozerMapper;

    @Override
    public List<TakenCourseDTO> getAllTakenCourses() {
        return takenCourseRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<TakenCourseDTO> getTakenCourseById(Long id) {
        return takenCourseRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public TakenCourse createTakenCourse(TakenCourseRequest request) {
        TakenCourse takenCourse = dozerMapper.map(request, TakenCourse.class);

        Course course = courseRepository.findByShortCode(request.getCourseShrtCode())
                .orElseThrow(() -> new RuntimeException("Course not found for shortCode: " + request.getCourseShrtCode()));
        takenCourse.setCourse(course);

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found for ID: " + request.getStudentId()));
        takenCourse.setStudent(student);

        return takenCourseRepository.save(takenCourse);
    }

    @Override
    public TakenCourse updateTakenCourse(TakenCourseRequest request) {


        TakenCourse existingTakenCourse = takenCourseRepository.findById(request.getId()).orElseThrow(
                () -> new RuntimeException("Taken course not found for ID: " + request.getId())
        );


                //dozerMapper.map(request, TakenCourse.class);

        //
        return takenCourseRepository.save(existingTakenCourse);
    }

    @Override
    public void deleteTakenCourse(Long id) {
        if (!takenCourseRepository.existsById(id)) {
            throw new RuntimeException("Taken course not found for ID: " + id);
        }
        takenCourseRepository.deleteById(id);
    }

    private TakenCourseDTO convertToDTO(TakenCourse takenCourse) {
        return dozerMapper.map(takenCourse, TakenCourseDTO.class);
    }
}
*/
package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.dto.TakenCourseDTO;
import com.etiya.studentinfosystem.postgredb.model.Course;
import com.etiya.studentinfosystem.postgredb.model.Student;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import com.etiya.studentinfosystem.postgredb.repository.CourseRepository;
import com.etiya.studentinfosystem.postgredb.repository.StudentRepository;
import com.etiya.studentinfosystem.postgredb.repository.TakenCourseRepository;
import com.etiya.studentinfosystem.postgredb.request.TakenCourseRequest;
import com.etiya.studentinfosystem.postgredb.service.TakenCourseService;
import com.github.dozermapper.core.Mapper;
import jakarta.transaction.Transactional;
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
    private Mapper dozerMapper;

    @Override
    public List<TakenCourseDTO> getAllTakenCourses() {
        return takenCourseRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<TakenCourseDTO> getTakenCourseById(Long id) {
        return takenCourseRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public TakenCourse createTakenCourse(TakenCourseRequest request) {
        TakenCourse takenCourse = dozerMapper.map(request, TakenCourse.class);

        Course course = courseRepository.findByShortCode(request.getCourseShrtCode())
                .orElseThrow(() -> new RuntimeException("Course not found for shortCode: " + request.getCourseShrtCode()));
        takenCourse.setCourse(course);

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found for ID: " + request.getStudentId()));
        takenCourse.setStudent(student);

        return takenCourseRepository.save(takenCourse);
    }

    @Override
    @Transactional
    public TakenCourse updateTakenCourse(TakenCourseRequest request) {
        TakenCourse existingTakenCourse = takenCourseRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Taken course not found for ID: " + request.getId()));

        existingTakenCourse.setTerm(request.getTerm());

        Course course = courseRepository.findByShortCode(request.getCourseShrtCode())
                .orElseThrow(() -> new RuntimeException("Course not found for shortCode: " + request.getCourseShrtCode()));
        existingTakenCourse.setCourse(course);

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found for ID: " + request.getStudentId()));
        existingTakenCourse.setStudent(student);

        existingTakenCourse.setIsActive(request.getIsActive());

        // Diğer potansiyel güncelleme alanlarınızı buraya ekleyebilirsiniz.

        return takenCourseRepository.save(existingTakenCourse);
    }

    @Override
    public void deleteTakenCourse(Long id) {
        if (!takenCourseRepository.existsById(id)) {
            throw new RuntimeException("Taken course not found for ID: " + id);
        }
        takenCourseRepository.deleteById(id);
    }

    private TakenCourseDTO convertToDTO(TakenCourse takenCourse) {
        return dozerMapper.map(takenCourse, TakenCourseDTO.class);
    }
}