package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.dto.CourseFeedbackDTO;
import com.etiya.studentinfosystem.postgredb.model.CourseFeedback;
import com.etiya.studentinfosystem.postgredb.repository.CourseFeedbackRepository;
import com.etiya.studentinfosystem.postgredb.service.CourseFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseFeedbackServiceImpl implements CourseFeedbackService {
    @Autowired
    private CourseFeedbackRepository feedbackRepository;

    @Override
    public CourseFeedbackDTO giveFeedback(CourseFeedbackDTO dto) {
        CourseFeedback feedback = toEntity(dto);
        feedback = feedbackRepository.save(feedback);
        return toDTO(feedback);
    }

    @Override
    public List<CourseFeedbackDTO> getFeedbacksForCourse(Long courseId) {
        List<CourseFeedback> feedbacks = feedbackRepository.findByCourseId(courseId);
        return feedbacks.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private CourseFeedback toEntity(CourseFeedbackDTO dto) {
        CourseFeedback feedback = new CourseFeedback();
        feedback.setFeedbackText(dto.getFeedbackText());
        feedback.setRating(dto.getRating());
        feedback.setFeedbackDate(dto.getFeedbackDate());
        // Diğer dönüşümler (Örneğin course ve student dönüşümleri) burada yapılabilir.
        return feedback;
    }

    private CourseFeedbackDTO toDTO(CourseFeedback feedback) {
        return new CourseFeedbackDTO(feedback.getFeedbackText(), feedback.getRating(), feedback.getFeedbackDate());
        // Diğer dönüşümler (Örneğin course ve student dönüşümleri) burada yapılabilir.
    }
}
