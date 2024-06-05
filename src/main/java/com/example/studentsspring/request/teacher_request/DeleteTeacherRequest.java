package com.example.studentsspring.request.teacher_request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTeacherRequest {
    @NotNull
    private  Long id;
}
