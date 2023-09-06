package com.etiya.studentinfosystem.postgredb.dto;

import lombok.Data;

@Data
public class TakenCourseDTO {
    private Long studentId;
    private String courseName;
    private String term;
    private Long gradeId;
    private int isActive;

}
