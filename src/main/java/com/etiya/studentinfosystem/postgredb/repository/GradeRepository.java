package com.etiya.studentinfosystem.postgredb.repository;

import com.etiya.studentinfosystem.postgredb.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
