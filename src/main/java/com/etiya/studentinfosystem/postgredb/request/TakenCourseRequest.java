package com.etiya.studentinfosystem.postgredb.request;

import lombok.Data;

@Data
public class TakenCourseRequest {

    private Long id;
    private Long studentId;
    private String courseShrtCode;
    private String term;
    private int isActive;
    private Long gradeId;

}
