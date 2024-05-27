package com.example.studentsspring.request.teacher_request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTeacherRequest {
    @NotNull
    private final Long id;
}
