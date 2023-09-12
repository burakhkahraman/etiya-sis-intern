package com.etiya.studentinfosystem.postgredb.dto;

import lombok.Data;

@Data
public class GraduationStatusDTO {

    private Long studentId;
    private int mandatoryCoursesRemaining;
    private int electiveCoursesRemaining;
    private boolean isEligibleForGraduation;
    private String message;
}
