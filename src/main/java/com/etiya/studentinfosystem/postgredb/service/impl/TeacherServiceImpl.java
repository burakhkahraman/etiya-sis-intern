package com.etiya.studentinfosystem.postgredb.service.impl;
import com.etiya.studentinfosystem.postgredb.dto.TeacherDTO;
import com.etiya.studentinfosystem.postgredb.model.Teacher;
import com.etiya.studentinfosystem.postgredb.repository.TeacherRepository;
import com.etiya.studentinfosystem.postgredb.service.TeacherService;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private Mapper dozerMapper;
    @Override
    public TeacherDTO getTeacherById(Long id) {
        return convertToDTO(teacherRepository.findById(id).orElse(null));
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = convertToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return convertToDTO(savedTeacher);
    }
    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher existingTeacher = teacherRepository.findById(id).orElse(null);
        if (existingTeacher != null) {
            existingTeacher.setFirstName(teacherDTO.getFirstName());
            existingTeacher.setLastName(teacherDTO.getLastName());
            existingTeacher.setExpertiseArea(teacherDTO.getExpertiseArea());
            return convertToDTO(teacherRepository.save(existingTeacher));
        }
        return null;
    }
    @Override
    public boolean deleteTeacher(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
    private TeacherDTO convertToDTO(Teacher teacher) {
        return dozerMapper.map(teacher, TeacherDTO.class);
    }

    private Teacher convertToEntity(TeacherDTO teacherDTO) {
        return dozerMapper.map(teacherDTO, Teacher.class);
    }
}
