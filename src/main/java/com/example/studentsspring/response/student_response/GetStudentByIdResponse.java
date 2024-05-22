package com.example.studentsspring.response.student_response;


import com.example.studentsspring.entity.Student;
import com.example.studentsspring.response.student_group_response.GetStudentGroupByIdResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetStudentByIdResponse {
    private final Long id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String status;
    private final GetStudentGroupByIdResponse group;

    public GetStudentByIdResponse(Student student){
        this(student.getId(),student.getLastName(),student.getFirstName(),student.getMiddleName(),student.getStatus(), new GetStudentGroupByIdResponse(student.getGroup()));
    }
}
