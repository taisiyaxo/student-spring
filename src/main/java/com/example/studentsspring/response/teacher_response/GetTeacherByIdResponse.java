package com.example.studentsspring.response.teacher_response;

import com.example.studentsspring.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetTeacherByIdResponse {
    private final Long id;
    private final String lastName;
    private final String firstName;
    private final String middleName;

    public GetTeacherByIdResponse(Teacher teacher){
        this.id = teacher.getId();
        this.lastName = teacher.getLastName();
        this.firstName = teacher.getFirstName();
        this.middleName = teacher.getMiddleName();
    }
}
