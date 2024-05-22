package com.example.studentsspring;

import com.example.studentsspring.entity.StudentGroup;
import com.example.studentsspring.repository.IStudentGroupRepository;
import jakarta.persistence.EntityManager;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentsSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(IStudentGroupRepository studentGroupRepository) {

		StudentGroup studentGroup = new StudentGroup();
		studentGroup.setName("1");
		return args -> {
			studentGroupRepository.save(studentGroup);
		};

	}
}
