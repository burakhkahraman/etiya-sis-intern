package com.etiya.studentinfosystem.postgredb.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentTakenCoursesDTO {

    private Long studentId;
    private String studentName;
    private String courseName;
    private String term;
    private List<ResultOfExamDTO> examResults;
}
