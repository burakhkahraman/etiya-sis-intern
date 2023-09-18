package com.etiya.studentinfosystem.postgredb.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "course_feedbacks")
public class CourseFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String feedbackText;

    @Column(nullable = false)
    private int rating;

    @Column
    private LocalDateTime feedbackDate = LocalDateTime.now();
}
