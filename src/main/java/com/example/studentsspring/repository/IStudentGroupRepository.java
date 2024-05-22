package com.example.studentsspring.repository;

import com.example.studentsspring.entity.Student;
import com.example.studentsspring.entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentGroupRepository extends JpaRepository<StudentGroup, Long> {

}
