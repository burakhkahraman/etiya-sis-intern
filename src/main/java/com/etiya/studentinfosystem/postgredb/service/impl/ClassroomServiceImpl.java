package com.etiya.studentinfosystem.postgredb.service.impl;

import com.etiya.studentinfosystem.postgredb.dto.ClassroomDTO;
import com.etiya.studentinfosystem.postgredb.model.Classroom;
import com.etiya.studentinfosystem.postgredb.repository.ClassroomRepository;
import com.etiya.studentinfosystem.postgredb.service.ClassroomService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    /*@Autowired
    private Mapper dozerMapper;*/
    private final Mapper dozerMapper = DozerBeanMapperBuilder.create()
            .withMappingFiles("DozerBeanConfig.xml") // XML dosyasının adını belirtiyoruz
            .build();


    @Override
    public List<ClassroomDTO> getAllClassrooms() {
        return classroomRepository.findAll()
                .stream()
                .map(classroom -> dozerMapper.map(classroom, ClassroomDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Classroom> getClassroomById(Long id) {
        return classroomRepository.findById(id);
    }

    @Override
    public Classroom createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom updateClassroom(Long id, Classroom updatedClassroom) {
        if (classroomRepository.existsById(id)) {
            updatedClassroom.setId(id);
            return classroomRepository.save(updatedClassroom);
        }
        return null;
    }

    @Override
    public void deleteClassroom(Long id) {
        classroomRepository.deleteById(id);
    }
}
