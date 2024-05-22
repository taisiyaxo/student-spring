package com.example.studentsspring.response.student_response;

import com.example.studentsspring.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddStudentResponse {
    private final Long id;

    public AddStudentResponse(Student student){
        this(student.getId());
    }
}
