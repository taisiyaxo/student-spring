package com.example.studentsspring.repository;

import com.example.studentsspring.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepository extends JpaRepository<Subject, Long> {
}
