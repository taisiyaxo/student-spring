package com.example.studentsspring.response.student_group_response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddStudentGroupResponse {
    private final Long id;
    private final String name;
}
