package com.etiya.studentinfosystem.postgredb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseFeedbackDTO {
    private String feedbackText;
    private int rating;
    private LocalDateTime feedbackDate;
}
