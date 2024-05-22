package com.example.studentsspring.repository;

import com.example.studentsspring.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByGroupId(Long groupId);

}
