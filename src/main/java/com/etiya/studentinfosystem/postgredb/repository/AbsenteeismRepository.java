package com.etiya.studentinfosystem.postgredb.repository;

import com.etiya.studentinfosystem.postgredb.model.Absenteeism;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenteeismRepository extends JpaRepository <Absenteeism,Long> {
}
