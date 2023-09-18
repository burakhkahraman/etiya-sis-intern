package com.etiya.studentinfosystem.postgredb.repository;

import com.etiya.studentinfosystem.postgredb.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findByMinScoreLessThanEqualAndMaxScoreGreaterThanEqual(double minScore, double maxScore);

}
