package com.etiya.studentinfosystem.postgredb.repository;

import com.etiya.studentinfosystem.postgredb.model.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamTypeRepository extends JpaRepository<ExamType, Long> {

    List<ExamType> findByExamGroup(Long examGroup);
}
