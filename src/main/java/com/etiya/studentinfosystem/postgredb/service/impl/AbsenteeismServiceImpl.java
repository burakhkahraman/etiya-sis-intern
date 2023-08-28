package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.model.Absenteeism;
import com.etiya.studentinfosystem.postgredb.repository.AbsenteeismRepository;
import com.etiya.studentinfosystem.postgredb.service.AbsenteeismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenteeismServiceImpl implements AbsenteeismService {
    @Autowired
    private AbsenteeismRepository absenteeismRepository;

    @Override
    public Absenteeism saveAbsenteeism(Absenteeism absenteeism) {
        return absenteeismRepository.save(absenteeism);
    }

    @Override
    public List<Absenteeism> findAllAbsenteeisms() {
        return absenteeismRepository.findAll();
    }

    @Override
    public Absenteeism findAbsenteeismById(Long id) {
        return absenteeismRepository.findById(id).orElse(null);
    }

    @Override
    public Absenteeism updateAbsenteeism(Long id, Absenteeism absenteeism) {
        if(absenteeismRepository.existsById(id)) {
            absenteeism.setId(id);
            return absenteeismRepository.save(absenteeism);
        }
        return null;
    }

    @Override
    public void deleteAbsenteeism(Long id) {
        absenteeismRepository.deleteById(id);
    }
}
