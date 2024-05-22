package com.example.studentsspring.request.student_group_request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteStudentGroupRequest {
    @NotNull
    private final Long id;
}
