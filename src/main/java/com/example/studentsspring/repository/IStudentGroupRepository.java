package com.example.studentsspring.repository;

import com.example.studentsspring.entity.Student;
import com.example.studentsspring.entity.StudentGroup;
import com.example.studentsspring.request.student_group_request.GetStudentGroupByIdRequest;
import com.example.studentsspring.response.student_group_response.GetStudentGroupByIdResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentGroupRepository extends JpaRepository<StudentGroup, Long> {
    GetStudentGroupByIdResponse getById(GetStudentGroupByIdRequest request);

}
