package com.example.studentsspring.response.student_response;


import com.example.studentsspring.entity.Student;
import com.example.studentsspring.response.student_group_response.GetStudentGroupByIdResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentByIdResponse {
    private Long id;
    private  String lastName;
    private  String firstName;
    private  String middleName;
    private  String status;
    private  GetStudentGroupByIdResponse group;

    public GetStudentByIdResponse(Student student){
        this(student.getId(),student.getLastName(),student.getFirstName(),student.getMiddleName(),student.getStatus(), new GetStudentGroupByIdResponse(student.getGroup()));
    }


}
