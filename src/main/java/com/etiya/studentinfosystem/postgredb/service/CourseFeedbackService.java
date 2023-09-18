package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.dto.CourseFeedbackDTO;
import com.etiya.studentinfosystem.postgredb.model.CourseFeedback;

import java.util.List;

public interface CourseFeedbackService {
    CourseFeedbackDTO giveFeedback(CourseFeedbackDTO feedbackDTO);
    List<CourseFeedbackDTO> getFeedbacksForCourse(Long courseId);
}
