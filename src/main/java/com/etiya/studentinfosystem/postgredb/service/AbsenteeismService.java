package com.etiya.studentinfosystem.postgredb.service;

import com.etiya.studentinfosystem.postgredb.dto.AbsenteeismDTO;
import com.etiya.studentinfosystem.postgredb.model.Absenteeism;

import java.util.List;

public interface AbsenteeismService {
    Absenteeism saveAbsenteeism(Absenteeism absenteeism);

    List<AbsenteeismDTO> findAllAbsenteeisms();

    Absenteeism findAbsenteeismById(Long id);

    Absenteeism updateAbsenteeism(Long id, Absenteeism absenteeism);

    void deleteAbsenteeism(Long id);
}
