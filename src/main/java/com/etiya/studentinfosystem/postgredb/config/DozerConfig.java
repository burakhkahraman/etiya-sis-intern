package com.etiya.studentinfosystem.postgredb.config;


import com.etiya.studentinfosystem.postgredb.dto.TeacherDTO;
import com.etiya.studentinfosystem.postgredb.model.Teacher;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DozerConfig {
    @Bean
    public Mapper dozerBeanMapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }
}
