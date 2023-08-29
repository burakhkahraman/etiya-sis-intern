package com.etiya.studentinfosystem.postgredb.repository;

import com.etiya.studentinfosystem.postgredb.model.ClassroomRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRelRepository extends JpaRepository<ClassroomRel,Long> {
}
