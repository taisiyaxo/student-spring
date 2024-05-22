package com.example.studentsspring.response.student_group_response;

import com.example.studentsspring.entity.StudentGroup;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetStudentGroupByIdResponse {
    private final Long id;
    private final String name;

    public GetStudentGroupByIdResponse(StudentGroup group){
        this(group.getId(),group.getName());
    }
}
