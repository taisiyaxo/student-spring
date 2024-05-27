package com.example.studentsspring.request.teacher_request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTeacherByIdRequest {
    @NotNull
    private Long id;
}
