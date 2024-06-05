package com.example.studentsspring.request.lesson_request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetLessonByIdRequest {
    @NotNull
    private Long id;
}
