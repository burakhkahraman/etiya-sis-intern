package com.etiya.studentinfosystem;

import com.etiya.studentinfosystem.postgredb.model.Role;
import com.etiya.studentinfosystem.postgredb.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EtiyaStudentInformationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtiyaStudentInformationSystemApplication.class, args);
	}


}

