package com.etiya.studentinfosystem.postgredb.dto;

import lombok.Data;

@Data
public class TakenCourseDTO {
    private Long id;
    private Long studentId;
    private Long courseId;
    private String term;
    private Long gradeId;
    private int isActive;
    private String shortCode;
}
