package com.etiya.studentinfosystem.postgredb.service.impl;
import com.etiya.studentinfosystem.postgredb.model.StudentPerformance;
import com.etiya.studentinfosystem.postgredb.model.TakenCourse;
import com.etiya.studentinfosystem.postgredb.repository.TakenCourseRepository;
import com.etiya.studentinfosystem.postgredb.service.StudentPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentPerformanceServiceImpl implements StudentPerformanceService {
    @Autowired
    private TakenCourseRepository takenCourseRepository;

    @Autowired
    private ExamTypeServiceImpl examTypeService;

    @Override
    public StudentPerformance analyzeStudentPerformance(Long studentId) {
        List<TakenCourse> takenCourses = takenCourseRepository.findByStudentId(studentId);
        Map<String, Double> courseAverages = calculateCourseAverages(studentId, takenCourses);

        String bestCourse = findBestCourse(courseAverages);
        String worstCourse = findWorstCourse(courseAverages);
        Double overallAverage = calculateOverallAverage(courseAverages);

        List<String> recommendations = generateRecommendations(worstCourse);

        return buildStudentPerformance(studentId, courseAverages, overallAverage, bestCourse, worstCourse, recommendations);
    }

    private Map<String, Double> calculateCourseAverages(Long studentId, List<TakenCourse> takenCourses) {
        Map<String, Double> courseAverages = new HashMap<>();
        for (TakenCourse takenCourse : takenCourses) {
            String courseName = takenCourse.getCourse().getCourseName();
            Double average = examTypeService.calculateAverageGradeForStudentInCourse(studentId, takenCourse.getCourse().getId());
            courseAverages.put(courseName, average);
        }
        return courseAverages;
    }

    private Double calculateOverallAverage(Map<String, Double> courseAverages) {
        return courseAverages.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    private String findBestCourse(Map<String, Double> courseAverages) {
        return courseAverages.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    private String findWorstCourse(Map<String, Double> courseAverages) {
        return courseAverages.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
    }

    private List<String> generateRecommendations(String worstCourse) {
        List<String> recommendations = new ArrayList<>();
        if (worstCourse != null && !worstCourse.isEmpty()) {
            recommendations.add("Bu dönem sonunda " + worstCourse + " dersine önem vermelisin.");
        }
        return recommendations;
    }

    private StudentPerformance buildStudentPerformance(Long studentId, Map<String, Double> courseAverages, Double overallAverage,
                                                       String bestCourse, String worstCourse, List<String> recommendations) {
        StudentPerformance performance = new StudentPerformance();
        performance.setStudentId(studentId);
        performance.setCourseAverages(courseAverages);
        performance.setOverallAverage(overallAverage);
        performance.setBestCourse(bestCourse);
        performance.setWorstCourse(worstCourse);
        performance.setRecommendations(recommendations);
        return performance;
    }
}
