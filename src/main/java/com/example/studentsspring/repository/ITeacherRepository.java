package com.example.studentsspring.repository;

import com.example.studentsspring.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
}
