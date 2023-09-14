package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.dto.AbsenteeismDTO;
import com.etiya.studentinfosystem.postgredb.model.Absenteeism;
import com.etiya.studentinfosystem.postgredb.repository.AbsenteeismRepository;
import com.etiya.studentinfosystem.postgredb.service.AbsenteeismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbsenteeismServiceImpl implements AbsenteeismService {
    @Autowired
    private AbsenteeismRepository absenteeismRepository;

    @Override
    public Absenteeism saveAbsenteeism(Absenteeism absenteeism) {
        return absenteeismRepository.save(absenteeism);
    }

    public List<AbsenteeismDTO> findAllAbsenteeisms() {
        return absenteeismRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AbsenteeismDTO convertToDTO(Absenteeism absenteeism) {
        AbsenteeismDTO dto = new AbsenteeismDTO();

        dto.setId(absenteeism.getId());
        dto.setDate(absenteeism.getDate());
        dto.setDuration(absenteeism.getDuration());
        dto.setIsActive(absenteeism.getIsActive());

        if(absenteeism.getStudent() != null) {
            dto.setStudentId(absenteeism.getStudent().getId());
            dto.setStudentName(absenteeism.getStudent().getFirstName() + " " + absenteeism.getStudent().getLastName());
        }

        return dto;
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
