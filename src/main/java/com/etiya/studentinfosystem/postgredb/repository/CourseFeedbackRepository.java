package com.etiya.studentinfosystem.postgredb.repository;

import com.etiya.studentinfosystem.postgredb.model.CourseFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, Long> {
    List<CourseFeedback> findByCourseId(Long courseId);

}
