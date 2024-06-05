package com.example.studentsspring.response.student_group_response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentGroupResponse {
    private  Long id;
    private String name;
}
