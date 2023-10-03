package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.dto.GraduationStatusDTO;
import com.etiya.studentinfosystem.postgredb.model.Course;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import com.etiya.studentinfosystem.postgredb.repository.CourseRepository;
import com.etiya.studentinfosystem.postgredb.repository.TakenCourseRepository;
import com.etiya.studentinfosystem.postgredb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TakenCourseRepository takenCourseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        if(courseRepository.existsById(id)) {
            course.setId(id);
            return courseRepository.save(course);
        }
        return null;
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public GraduationStatusDTO checkGraduationStatus(Long studentId) {
        List<TakenCourse> takenCourses = takenCourseRepository.findByStudentId(studentId);

        long mandatoryCoursesTaken = takenCourses.stream()
                .filter(takenCourse -> Boolean.TRUE.equals(takenCourse.getCourse().getIsMandatory()))
                .count();

        long electiveCoursesTaken = takenCourses.stream()
                .filter(takenCourse -> Boolean.FALSE.equals(takenCourse.getCourse().getIsMandatory()))
                .count();

        GraduationStatusDTO graduationStatus = new GraduationStatusDTO();
        graduationStatus.setMandatoryCoursesRemaining(20 - (int) mandatoryCoursesTaken);
        graduationStatus.setElectiveCoursesRemaining(10 - (int) electiveCoursesTaken);

        if (mandatoryCoursesTaken >= 20 && electiveCoursesTaken >= 10) {
            graduationStatus.setEligibleForGraduation(true);
            graduationStatus.setMessage("Öğrenci mezun olabilir.");
        } else {
            graduationStatus.setEligibleForGraduation(false);
            graduationStatus.setMessage("Mezun olabilmek için " + graduationStatus.getMandatoryCoursesRemaining() +
             " tane zorunlu ve " + graduationStatus.getElectiveCoursesRemaining() + " tane seçmeli dersiniz kalmıştır.");
        }

        return graduationStatus;
    }
}