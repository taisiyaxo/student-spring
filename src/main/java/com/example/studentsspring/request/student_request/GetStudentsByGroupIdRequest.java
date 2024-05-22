package com.example.studentsspring.request.student_request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetStudentsByGroupIdRequest {
    @NotNull
    private  final  Long id;
}
