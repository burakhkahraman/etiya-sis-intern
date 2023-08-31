package com.etiya.studentinfosystem.postgredb.config;

import com.etiya.studentinfosystem.postgredb.dto.ResultOfExamDTO;
import com.etiya.studentinfosystem.postgredb.model.ResultOfExam;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(ResultOfExam.class, ResultOfExamDTO.class)
                .addMapping(src -> src.getExamType().getId(), ResultOfExamDTO::setExamTypeId)
                .addMapping(src -> src.getTakenCourse().getId(), ResultOfExamDTO::setTakenCourseId);

        return mapper;
    }

}
