package com.example.studentsspring.request.student_group_request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetStudentGroupByIdRequest {
    @NotNull
    private final Long id;
}
