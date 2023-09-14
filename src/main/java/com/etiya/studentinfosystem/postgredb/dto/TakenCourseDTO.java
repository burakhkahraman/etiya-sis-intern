package com.etiya.studentinfosystem.postgredb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakenCourseDTO {
    private Long studentId;
    private String studentName;
    private String courseName;
    private String term;
    private String gradeLetter;
    private int isActive;
    private List<ResultOfExamDTO> examResults;
}
