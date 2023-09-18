package com.etiya.studentinfosystem.postgredb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentPerformance {

        private Long studentId;
        private Map<String, Double> courseAverages; // <CourseName, AverageScore>
        private Double overallAverage;
        private String bestCourse;
        private String worstCourse;
        private List<String> recommendations;
    }

