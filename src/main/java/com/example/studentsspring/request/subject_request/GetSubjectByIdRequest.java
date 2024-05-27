package com.example.studentsspring.request.subject_request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSubjectByIdRequest {
    @NotNull
    private final Long id;
}
