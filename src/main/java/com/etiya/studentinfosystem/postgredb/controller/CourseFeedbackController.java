package com.etiya.studentinfosystem.postgredb.controller;

import com.etiya.studentinfosystem.postgredb.dto.CourseFeedbackDTO;
import com.etiya.studentinfosystem.postgredb.model.CourseFeedback;
import com.etiya.studentinfosystem.postgredb.service.CourseFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseFeedbackController {
    @Autowired
    private CourseFeedbackService feedbackService;

    @PostMapping("/{courseId}/feedbackStudent")
    public ResponseEntity<CourseFeedbackDTO> giveFeedback(@PathVariable Long courseId,
                                                          @RequestBody CourseFeedbackDTO feedbackDTO) {
        CourseFeedbackDTO savedFeedback = feedbackService.giveFeedback(feedbackDTO);
        return ResponseEntity.ok(savedFeedback);
    }

    @GetMapping("/{courseId}/feedbackTeacher")
    public ResponseEntity<List<CourseFeedbackDTO>> getFeedbacksForCourse(@PathVariable Long courseId) {
        List<CourseFeedbackDTO> feedbacks = feedbackService.getFeedbacksForCourse(courseId);
        return ResponseEntity.ok(feedbacks);
    }
}
